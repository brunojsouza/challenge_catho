package br.com.souzabrunoj.service.networking.data.local

import br.com.souzabrunoj.domain.data.response.login.LoginModel

interface PreferencesService {

    fun saveUser(user: LoginModel)

    fun getCredentials(): Pair<String, String>

    fun hasValidCredentials(): Boolean

}