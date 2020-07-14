package br.com.souzabrunoj.challenge_catho.presentation

import androidx.lifecycle.*
import br.com.souzabrunoj.challenge_catho.common.Constants.LIKE_SURVEY
import br.com.souzabrunoj.challenge_catho.common.Constants.UNLIKE_SURVEY
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val dispatcher = Dispatchers.Main
    private lateinit var tip: TipModel
    private var tipsList = mutableListOf<TipModel>()
    private val positions = MutableLiveData<ViewState<List<PositionModel>>>()
    private val login = MutableLiveData<ViewState<LoginModel>>()
    private val tips = MutableLiveData<ViewState<TipModel>>()
    private val keys = MutableLiveData<ViewState<Unit>>()
    private val like = MutableLiveData<Unit>()
    private val unlike = MutableLiveData<Unit>()
    private val showRemoveTipsButton = MutableLiveData<Boolean>()

    fun positionsObserver(): LiveData<ViewState<List<PositionModel>>> = positions
    fun loginObserver(): LiveData<ViewState<LoginModel>> = login
    fun tipObserver(): LiveData<ViewState<TipModel>> = tips
    fun keysObserver(): LiveData<ViewState<Unit>> = keys
    fun likeObserver(): LiveData<Unit> = like
    fun unlikeObserver(): LiveData<Unit> = unlike
    fun showRemoveTipsButtonObserver(): LiveData<Boolean> = showRemoveTipsButton

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
        viewModelScope.launch {
            repository.doLogin("ee09bd39-4ca2-47ac-9c5e-9c57ba5a26dc").either(::handleLoginFailure, ::handleLoginSuccess)
        }
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
        viewModelScope.launch {
            delay(1000)
            repository.getPositions().either(::handleGetPositionsFailure, ::handleGetPositionsSuccess)
        }
    }

    private fun handleGetPositionsSuccess(response: List<PositionModel>) {
        positions.postSuccess(response)
    }

    private fun handleGetPositionsFailure(failure: Failure) {
        positions.postFailure(failure)
    }

    private fun getTips() {
        viewModelScope.launch {
            delay(2000)
            repository.getTips().either(::handleGetTipsFailure, ::handGetTipsSuccess)
        }
    }

    private fun handGetTipsSuccess(response: List<TipModel>) {
        tipsList.addAll(response)
        choiceTipToShow()
    }

    private fun handleGetTipsFailure(failure: Failure) {
        tips.postFailure(failure)
    }

    fun sendTipSurvey(interactionType: String) {
        viewModelScope.launch {
            repository.sendTipSurvey(tipId = tip.id, interactionType = interactionType)
                .either(::handleTipSurveyFailure) { handTipSurveySuccess(it, interactionType) }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun handTipSurveySuccess(response: SurveyModel, interactionType: String) {
        when (interactionType) {
            LIKE_SURVEY -> like.value = Unit
            UNLIKE_SURVEY -> unlike.value = Unit
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun handleTipSurveyFailure(failure: Failure) {

    }

    private fun choiceTipToShow() {
        val position = getRandomNumber(end = tipsList.size)
        tip = tipsList[position]
        tips.postSuccess(tip)
        showRemoveTipsButton.value = tipsList.size > 1
    }

    fun showNextTips() {
        tips.postLoading()
        GlobalScope.launch(context = dispatcher) {
            delay(1000)
            tipsList.firstOrNull { it.id == tip.id }?.let { tipsList.remove(it) }
            choiceTipToShow()
        }
    }

    fun getTipUrl() = tip.button.url

    private fun getRandomNumber(start: Int = 0, end: Int): Int = Random.nextInt(start, end)
}