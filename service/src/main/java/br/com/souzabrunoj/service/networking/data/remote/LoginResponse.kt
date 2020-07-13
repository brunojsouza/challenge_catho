package br.com.souzabrunoj.service.networking.data.remote

import br.com.souzabrunoj.domain.data.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("token") val token: String?,
    @SerializedName("photo") val photoUrl: String?
): BaseResponse()