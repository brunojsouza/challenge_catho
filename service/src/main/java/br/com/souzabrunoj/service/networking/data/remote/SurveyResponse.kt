package br.com.souzabrunoj.service.networking.data.remote

import br.com.souzabrunoj.domain.data.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class SurveyResponse(
    @SerializedName("action") val action: String?,
    @SerializedName("created-at") val createdAt: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("tipId") val tipId: String?
) : BaseResponse()