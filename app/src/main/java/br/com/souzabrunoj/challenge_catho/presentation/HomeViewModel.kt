package br.com.souzabrunoj.challenge_catho.presentation

import android.os.Handler
import androidx.lifecycle.*
import br.com.souzabrunoj.challenge_catho.common.ViewState
import br.com.souzabrunoj.challenge_catho.common.postFailure
import br.com.souzabrunoj.challenge_catho.common.postLoading
import br.com.souzabrunoj.challenge_catho.common.postSuccess
import br.com.souzabrunoj.domain.common.Failure
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.domain.data.response.position.PositionModel
import br.com.souzabrunoj.domain.data.response.tips.TipModel
import br.com.souzabrunoj.domain.data.response.tips.survey.SurveyModel
import br.com.souzabrunoj.repository.Repository
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private lateinit var tip: TipModel
    private val positions = MutableLiveData<ViewState<List<PositionModel>>>()
    private val login = MutableLiveData<ViewState<LoginModel>>()
    private val tips = MutableLiveData<ViewState<TipModel>>()
    private val keys = MutableLiveData<ViewState<Unit>>()

    fun positionsObserver(): LiveData<ViewState<List<PositionModel>>> = positions
    fun loginObserver(): LiveData<ViewState<LoginModel>> = login
    fun tipObserver(): LiveData<ViewState<TipModel>> = tips
    fun keysObserver(): LiveData<ViewState<Unit>> = keys

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getApiKeys()
    }

    private fun getApiKeys() {
        viewModelScope.launch {
            repository.getApiKey().either(::handleGetApiFailure, ::handleGetApisSuccess)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun handleGetApisSuccess(response: Unit) {
        doLogin()
    }

    private fun handleGetApiFailure(failure: Failure) {
        keys.postFailure(failure)
    }

    private fun doLogin() {
        login.postLoading()
        tips.postLoading()
        positions.postLoading()
        Handler().postDelayed({
            viewModelScope.launch {
                repository.doLogin("ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc").either(::handleLoginFailure, ::handleLoginSuccess)
            }
        }, 2000L)
    }

    private fun handleLoginSuccess(response: LoginModel) {
        login.postSuccess(response)
        getPositions()
        getTips()
    }

    private fun handleLoginFailure(failure: Failure) {
        login.postFailure(failure)
    }

    private fun getPositions() {
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

    private fun getTips() {
        Handler().postDelayed(
            {
                viewModelScope.launch {
                    repository.getTips().either(::handleGetTipsFailure, ::handGetTipsSuccess)
                }
            }, 3000L
        )
    }

    private fun handGetTipsSuccess(response: List<TipModel>) {
        val position = Random.nextInt(0, response.size)
        tip = response[position]
        tips.postSuccess(tip)
    }

    private fun handleGetTipsFailure(failure: Failure) {
        tips.postFailure(failure)
    }

    fun sendTipSurvey(interactionType: String) {
        viewModelScope.launch {
            repository.sendTipSurvey(tipId = tip.id, interactionType = interactionType).either(::handleTipSurveyFailure, ::handTipSurveySuccess)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun handTipSurveySuccess(response: SurveyModel) {
        tips.postSuccess(tip)
    }

    private fun handleTipSurveyFailure(failure: Failure) {
        tips.postFailure(failure)
    }
}