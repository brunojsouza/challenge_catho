package br.com.souzabrunoj.service.networking.data.mock

import br.com.souzabrunoj.service.networking.data.key.KeyResponse
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.SurveyResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

fun doApiKeysFromMock(): KeyResponse = fromJson("keys_response.json")

fun doLoginFromMock(): LoginResponse = fromJson("login_response.json")

fun getPositionsFromMock(): List<PositionResponse> = fromJson("positions_response.json")

fun getTipsFromMock(): List<TipResponse> = fromJson("tips_response.json")

fun sendTipSurveyFromMock(): SurveyResponse = fromJson("survey_tips_response.json")