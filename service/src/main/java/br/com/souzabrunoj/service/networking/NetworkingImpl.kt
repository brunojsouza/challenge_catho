package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.service.common.BaseNetworking
import br.com.souzabrunoj.service.networking.data.key.KeyResponse
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

class NetworkingImpl(private val webService: WebService) : Networking, BaseNetworking() {

    override suspend fun getApiKey(): KeyResponse = doRequest { webService.getApiKey() }

    override suspend fun doLogin(apiKey: String, userId: String): LoginResponse = /*doLoginFromMock()*/ doRequest { webService.doLogin(apiKey, userId) }

    override suspend fun getPositions(apiKey: String): List<PositionResponse> =  /*getPositionsFromMock()*/ doRequest { webService.getPositions(apiKey) }

    override suspend fun getTips(apiKey: String): List<TipResponse> = /*getTipsFromMock()*/ doRequest { webService.getTips(apiKey) }
}