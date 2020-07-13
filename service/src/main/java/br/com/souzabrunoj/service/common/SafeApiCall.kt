package br.com.souzabrunoj.service.common

import android.content.Context
import br.com.souzabrunoj.domain.common.KoinInjector
import br.com.souzabrunoj.domain.common.getStringFromResources
import br.com.souzabrunoj.service.R
import org.koin.core.get

typealias Block<R> = suspend () -> R

sealed class SafeResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : SafeResult<T>()

    data class Error<out T : Any>(val exception: Throwable, val response: T?) : SafeResult<T>()
}

open class SafeResponse {

    suspend fun <R : Any> safeApiCall(block: Block<R>): SafeResult<R> = load(block)

    private suspend fun <R : Any> load(block: Block<R>): SafeResult<R> {
        var response: R? = null
        return try {
            checkNetworkConnection()
            response = block.invoke()
            SafeResult.Success(response)
        } catch (error: Throwable) {
            error.printStackTrace()
            SafeResult.Error(error, response)
        }
    }

    @Throws(Throwable::class)
    private fun checkNetworkConnection() {
        if (KoinInjector.get<Context>().isOnline().not()) {
            throw NetworkConnectionException(getStringFromResources(R.string.network_no_internet_connection))
        }
    }
}
