package br.com.souzabrunoj.domain.data.response.login

data class LoginModel(
    val id: String,
    val name: String,
    val token: String,
    val photoUrl: String
)