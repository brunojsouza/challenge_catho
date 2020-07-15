package br.com.souzabrunoj.service.common

import android.content.Context
import br.com.souzabrunoj.domain.common.KoinInjector
import br.com.souzabrunoj.domain.common.getStringFromResources
import br.com.souzabrunoj.service.R
import org.koin.core.KoinComponent
import org.koin.core.get

abstract class BaseNetworking : KoinComponent {
    suspend fun <R : Any> safeApiCall(block: Block<R>): R {
        var response: R? = null
        return try {
            checkNetworkConnection()
            response = block.invoke()
            SafeResult.Success(response)
        } catch (error: Throwable) {
            error.printStackTrace()
            SafeResult.Error(error, response)
        }.run {
            when (this) {
                is SafeResult.Success -> data
                is SafeResult.Error -> throw exception.toNetworkingError()
            }
        }
    }

    @Throws(Throwable::class)
    private fun checkNetworkConnection() {
        if (KoinInjector.get<Context>().isOnline().not()) {
            throw NetworkConnectionException(getStringFromResources(R.string.network_no_internet_connection))
        }
    }
}