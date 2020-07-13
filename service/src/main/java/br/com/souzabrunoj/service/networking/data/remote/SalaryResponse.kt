package br.com.souzabrunoj.service.networking.data.remote

import com.google.gson.annotations.SerializedName

data class SalaryResponse(
    @SerializedName("range") val range: String?,
    @SerializedName("real") val real: String?
)