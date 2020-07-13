package br.com.souzabrunoj.challenge_catho.presentation

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.souzabrunoj.challenge_catho.common.ViewState
import br.com.souzabrunoj.challenge_catho.common.postFailure
import br.com.souzabrunoj.challenge_catho.common.postLoading
import br.com.souzabrunoj.challenge_catho.common.postSuccess
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val positions = MutableLiveData<ViewState<List<PositionModel>>>()
    private val login = MutableLiveData<ViewState<LoginModel>>()
    private val tips = MutableLiveData<ViewState<List<TipModel>>>()

    fun positionsObserver(): LiveData<ViewState<List<PositionModel>>> = positions
    fun loginObserver(): LiveData<ViewState<LoginModel>> = login
    fun tipObserver(): LiveData<ViewState<List<TipModel>>> = tips

    fun doLogin() {
        login.postLoading()
        viewModelScope.launch {
            repository.doLogin().either(::handleLoginFailure, ::handleLoginSuccess)
        }
    }

    private fun handleLoginSuccess(response: LoginModel) {
        login.postSuccess(response)
    }

    private fun handleLoginFailure(failure: Failure) {
        login.postFailure(failure)
    }

    fun getPositions() {
        positions.postLoading()
        Handler().postDelayed(
            {
                viewModelScope.launch {
                    repository.getPositions().either(::handleGetPositionsFailure, ::handleGetPositionsSuccess)
                }
            }, 2000L
        )
    }

    private fun handleGetPositionsSuccess(response: List<PositionModel>) {
        positions.postSuccess(response)
    }

    private fun handleGetPositionsFailure(failure: Failure) {
        positions.postFailure(failure)
    }


    fun getTips() {
        tips.postLoading()
        Handler().postDelayed(
            {
                viewModelScope.launch {
                    repository.getTips().either(::handleGetTipsFailure, ::handGetTipsSuccess)
                }
            }, 2000L
        )
    }

    private fun handGetTipsSuccess(response: List<TipModel>) {
        tips.postSuccess(response)
    }

    private fun handleGetTipsFailure(failure: Failure) {
        tips.postFailure(failure)
    }
}