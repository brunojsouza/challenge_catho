package br.com.souzabrunoj.service.networking

import br.com.souzabrunoj.domain.common.X_API_KEY
import br.com.souzabrunoj.service.networking.data.key.KeyResponse
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse
import br.com.souzabrunoj.service.networking.data.remote.PositionResponse
import br.com.souzabrunoj.service.networking.data.remote.SurveyResponse
import br.com.souzabrunoj.service.networking.data.remote.TipResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface WebService {
    @GET("keys")
    suspend fun getApiKey(): KeyResponse

    @GET("auth/{userId}")
    suspend fun doLogin(@Header(X_API_KEY) apiKey: String, @Path("userId") userId: String): LoginResponse

    @GET("suggestion")
    suspend fun getPositions(@Header(X_API_KEY) apiKey: String): List<PositionResponse>

    @GET("tips")
    suspend fun getTips(@Header(X_API_KEY) apiKey: String): List<TipResponse>

    @POST("survey/tips/{tipId}/{action}")
    suspend fun sendTipSurvey(
        @Header(X_API_KEY) apiKey: String, @Path("tipId") tipId: String, @Path("action") interactionType: String
    ): SurveyResponse
}