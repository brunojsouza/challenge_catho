package br.com.souzabrunoj.service.repository.mappers

import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.service.BuildConfig
import br.com.souzabrunoj.service.networking.data.remote.LoginResponse

fun LoginResponse.toLoginModel() = LoginModel(
    id = this.id ?: "",
    name = this.name ?: "",
    token = this.token ?: "",
    photoUrl = "${BuildConfig.BASE_URL}${this.photoUrl?.drop(1)}"
)