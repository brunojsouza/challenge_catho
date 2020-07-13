package br.com.souzabrunoj.challenge_catho.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private val positions = MutableLiveData<List<PositionModel>>()
    private val login = MutableLiveData<LoginModel>()
    private val tips = MutableLiveData<List<TipModel>>()

    fun positionsObserver(): LiveData<List<PositionModel>> = positions
    fun loginObserver(): LiveData<LoginModel> = login
    fun tipObserver(): LiveData<List<TipModel>> = tips

    fun doLogin() {
        viewModelScope.launch {
            repository.doLogin().either(::handleLoginFailure, ::handleLoginSuccess)
        }
    }

    private fun handleLoginSuccess(response: LoginModel) {
        login.value = response
    }

    private fun handleLoginFailure(failure: Failure) {
        val s = failure.message
    }

    fun getPositions() {
        viewModelScope.launch {
            repository.getPositions().either(::handleGetPositionsFailure, ::handleGetPositionsSuccess)
        }
    }

    private fun handleGetPositionsSuccess(response: List<PositionModel>) {
        positions.value = response
    }

    private fun handleGetPositionsFailure(failure: Failure) {
        val s = failure.message
    }


    fun getTips() {
        viewModelScope.launch {
            repository.getTips().either(::handleGetTipsFailure, ::handGetTipsSuccess)
        }
    }

    private fun handGetTipsSuccess(response: List<TipModel>) {
        tips.value = response
    }

    private fun handleGetTipsFailure(failure: Failure) {
        val s = failure.message
    }

}