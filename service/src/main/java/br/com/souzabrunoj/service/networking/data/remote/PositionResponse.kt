package br.com.souzabrunoj.service.networking.data.remote

import br.com.souzabrunoj.domain.data.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class PositionResponse(
    @SerializedName("company") val company: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("jobAdTile") val jobAdTile: String?,
    @SerializedName("locations") val locations: List<String>?,
    @SerializedName("salary") val salaryModel: SalaryResponse?,
    @SerializedName("totalPositions") val totalPositions: Int?
): BaseResponse()