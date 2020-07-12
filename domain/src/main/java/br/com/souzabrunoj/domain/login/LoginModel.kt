package br.com.souzabrunoj.domain.login

data class LoginModel(
    val id: String,
    val name: String,
    val token: String,
    val photoUrl: String
)