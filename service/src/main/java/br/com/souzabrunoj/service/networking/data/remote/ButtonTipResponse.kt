package br.com.souzabrunoj.service.networking.data.remote

import com.google.gson.annotations.SerializedName

data class ButtonTipResponse(
    @SerializedName("label") val label: String?,
    @SerializedName("show") val show: Boolean?,
    @SerializedName("url") val url: String?
)