package br.com.souzabrunoj.challenge_catho.common

import androidx.lifecycle.MutableLiveData
import br.com.souzabrunoj.domain.common.Failure

class ViewState<D>(private val status: ViewStatus, val data: D? = null, val error: Throwable? = null, val failure: Failure? = null) {

    fun handleIt(
        success: (D) -> Unit = {},
        error: (Throwable) -> Unit = {},
        failure: (Failure) -> Unit = {},
        loading: () -> Unit = {},
        stopLoading: () -> Unit = {},
        neutral: () -> Unit = {}
    ): ViewState<D> {

        when (this.status) {
            ViewStatus.SUCCESS -> {
                stopLoading()
                this.data?.let { success(it) }
            }
            ViewStatus.ERROR -> {
                stopLoading()
                this.error?.let { error(it) }
            }
            ViewStatus.LOADING -> {
                loading()
            }
            ViewStatus.NEUTRAL -> {
                neutral()
                stopLoading()
            }
            ViewStatus.FAILURE -> {
                stopLoading()
                this.failure?.let { failure(it) }
            }
        }

        return this
    }

    enum class ViewStatus {
        LOADING, SUCCESS, ERROR, NEUTRAL, FAILURE
    }
}

fun <T> MutableLiveData<ViewState<T>>.postSuccess(data: T?) {
    this.postValue(ViewState(ViewState.ViewStatus.SUCCESS, data, null))
}

fun <T> MutableLiveData<ViewState<T>>.postLoading() {
    this.postValue(ViewState(ViewState.ViewStatus.LOADING, this.value?.data, this.value?.error))
}

fun <T> MutableLiveData<ViewState<T>>.postFailure(failure: Failure) {
    this.postValue(ViewState(ViewState.ViewStatus.FAILURE, failure = failure))
}


