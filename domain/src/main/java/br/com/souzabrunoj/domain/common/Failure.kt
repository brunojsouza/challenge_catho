package br.com.souzabrunoj.domain.common

import br.com.souzabrunoj.domain.R
import br.com.souzabrunoj.domain.common.Failure.FeatureFailure

/**
 * Base Class for handling errorsLiveData/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object NotFound : Failure()
    object Unknown : Failure()

    /**
     * A standard error response from a service
     *
     *  @property message provides a error message obtained from a service
     *  @property httpCode provides a error code
     *  @property errorCode provides a alternative error code
     *
     */
    data class ServiceError(
        private val messageInternal: String? = null,
        val httpCode: Int? = null,
        val errorCode: String? = null
    ) : Failure() {
        override var message = messageInternal?.takeIf { it.isNotEmpty() } ?: getStringFromResources(R.string.generic_error)
    }

    data class InternalError(val messageInternal: String) : Failure() {
        override val message = messageInternal
    }

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()

    open val message: String = ""
}