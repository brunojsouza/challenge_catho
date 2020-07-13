package br.com.souzabrunoj.service.networking.data.local

import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.service.networking.data.key.KeyResponse

interface PreferencesService {

    fun saveUser(user: LoginModel)

    fun getCredentials(): Pair<String, String>

    fun hasValidCredentials(): Boolean

    fun saveKeys(keys: KeyResponse)

    fun getApiKey(key: String) = String()

}