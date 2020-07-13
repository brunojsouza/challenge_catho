package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.service.networking.data.key.KeyResponse
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse

interface Networking {
    suspend fun getApiKey(): KeyResponse

    suspend fun doLogin(apiKey: String, userId: String): LoginResponse

    suspend fun getPositions(apiKey: String): List<PositionResponse>

    suspend fun getTips(apiKey: String): List<TipResponse>
}