package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.service.common.BaseNetworking
import br.com.souzabrunoj.service.networking.data.mock.doLoginFromMock
import br.com.souzabrunoj.service.networking.data.mock.getPositionsFromMock
import br.com.souzabrunoj.service.networking.data.mock.getTipsFromMock
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

class NetworkingImpl(private val webService: WebService) : Networking, BaseNetworking() {
    override suspend fun doLogin(): LoginResponse = doLoginFromMock() //doRequest { webService.doLogin() }

    override suspend fun getPositions(): List<PositionResponse> = getPositionsFromMock()//doRequest { webService.getPosition() }

    override suspend fun getTips(): List<TipResponse> = getTipsFromMock() //doRequest { webService.getTips() }
}