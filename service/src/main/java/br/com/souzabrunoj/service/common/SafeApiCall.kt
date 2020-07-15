package br.com.souzabrunoj.service.common

typealias Block<R> = suspend () -> R

sealed class SafeResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : SafeResult<T>()

    data class Error<out T : Any>(val exception: Throwable, val response: T?) : SafeResult<T>()
}

