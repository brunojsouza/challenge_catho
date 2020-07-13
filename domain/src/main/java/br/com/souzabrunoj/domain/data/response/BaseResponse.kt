package br.com.souzabrunoj.domain.data.response

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("mensagem")
    private val message: String? = ""

    @SerializedName("error")
    private val errorCode: String? = null

    @SerializedName("erro")
    private val errorBody: String? = null

    @SerializedName("error_description")
    private val errorDescription: String? = null

    @SerializedName("message")
    private val defaultMessage: String? = null

    fun getMessage(): String? {
        return message ?: errorDescription ?: defaultMessage
    }

    fun getErrorCode(): String? {
        return errorCode ?: errorCode
    }
}