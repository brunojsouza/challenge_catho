package br.com.souzabrunoj.service.networking.data.key

import com.google.gson.annotations.SerializedName

data class KeyResponse(
    @SerializedName("auth") val authKey: String?,
    @SerializedName("suggestion") val suggestionKey: String?,
    @SerializedName("survey") val surveyKey: String?,
    @SerializedName("tips") val tipsKey: String?
)