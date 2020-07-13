package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

interface Networking {
    suspend fun doLogin(): LoginResponse

    suspend fun getPositions(): List<PositionResponse>

    suspend fun getTips(): List<TipResponse>
}