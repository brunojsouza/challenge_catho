package br.com.souzabrunoj.service.networking.data.remote

import br.com.souzabrunoj.domain.data.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class TipResponse(
    @SerializedName("button") val button: ButtonTipResponse?,
    @SerializedName("description") val description: String?,
    @SerializedName("id") val id: String?
): BaseResponse()