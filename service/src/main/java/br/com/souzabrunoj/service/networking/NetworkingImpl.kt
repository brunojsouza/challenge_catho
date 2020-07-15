package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.service.BuildConfig
import br.com.souzabrunoj.service.common.BaseNetworking
import br.com.souzabrunoj.service.common.networking_connection.NetworkingConnection
import br.com.souzabrunoj.service.networking.data.key.KeyResponse
import br.com.souzabrunoj.service.networking.data.mock.*
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.SurveyResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

class NetworkingImpl(private val webService: WebService, checkNetworkingConnection: NetworkingConnection) : Networking, BaseNetworking(checkNetworkingConnection) {

    override suspend fun getApiKey(): KeyResponse = if (BuildConfig.ENABLE_MOCK) getApiKeysFromMock() else safeApiCall { webService.getApiKey() }

    override suspend fun doLogin(apiKey: String, userId: String): LoginResponse = if (BuildConfig.ENABLE_MOCK) doLoginFromMock() else
        safeApiCall { webService.doLogin(apiKey, userId) }

    override suspend fun getPositions(apiKey: String): List<PositionResponse> = if (BuildConfig.ENABLE_MOCK) getPositionsFromMock() else
        safeApiCall { webService.getPositions(apiKey) }

    override suspend fun getTips(apiKey: String): List<TipResponse> =
        if (BuildConfig.ENABLE_MOCK) getTipsFromMock() else safeApiCall { webService.getTips(apiKey) }

    override suspend fun sendTipSurvey(apiKey: String, tipId: String, interactionType: String): SurveyResponse =
        if (BuildConfig.ENABLE_MOCK) sendTipSurveyFromMock() else
            safeApiCall { webService.sendTipSurvey(apiKey, tipId, interactionType) }
}