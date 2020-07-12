package br.com.souzabrunoj.service.common

import java.io.IOException

data class NetworkingError(
    override val message: String?,
    val errorCode: String? = null,
    val httpCode: Int? = null,
    val errorDescription: String? = null,
    val httpErrorDescription: String? = null,
    val originalError: Throwable? = null
) : Throwable()

class NetworkConnectionException(override val message: String) : IOException(message)
