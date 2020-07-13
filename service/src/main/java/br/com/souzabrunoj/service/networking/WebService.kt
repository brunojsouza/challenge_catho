package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse
import retrofit2.http.GET

interface WebService {
    @GET("keys")
    suspend fun doLogin(): LoginResponse

    @GET("suggestion")
    suspend fun getPositions(): List<PositionResponse>

    @GET("tips")
    suspend fun getTips(): List<TipResponse>
}