package br.com.souzabrunoj.service.common

import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseNetworking : KoinComponent {
    private val safeResponse: SafeResponse by inject()
    protected suspend fun <R : Any> doRequest(block: suspend () -> R): R {
        return safeResponse.safeApiCall { block() }.run {
            when (this) {
                is SafeResult.Success -> data
                is SafeResult.Error -> throw exception.toNetworkingError()
            }
        }
    }
}