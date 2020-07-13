package br.com.souzabrunoj.repository

import br.com.souzabrunoj.domain.common.Either
import br.com.souzabrunoj.domain.common.Error
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.service.common.NetworkingError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    protected suspend fun <R : Any?> shiftThread(block: suspend () -> Either<Failure.ServiceError, R>) =
        withContext(Dispatchers.IO) {
            try {
                block()
            } catch (error: Throwable) {
                when (error) {
                    is NetworkingError -> Error(Failure.ServiceError(error.errorDescription ?: error.message, httpCode = error.httpCode))
                    else -> Error(Failure.ServiceError(messageInternal = error.message, httpCode = (error as? HttpException)?.code()))
                }
            }
        }
}