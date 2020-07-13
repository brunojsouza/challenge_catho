package br.com.souzabrunoj.service.networking.data.local

import android.content.Context
import android.content.SharedPreferences
import br.com.souzabrunoj.domain.common.*
import br.com.souzabrunoj.domain.data.response.login.LoginModel
import br.com.souzabrunoj.service.networking.data.key.KeyResponse

class PreferenceServiceImpl(private val context: Context) : PreferencesService {
    private val preferences: SharedPreferences by lazy { context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE) }

    private fun saveCredentials(userToken: String?) = with(preferences.edit()) {
        putString(AUTHORIZATION, "$userToken")
        commit()
    }

    override fun saveUser(user: LoginModel) {
        with(preferences.edit()) {
            saveCredentials(user.token)
            commit()
        }
    }

    override fun saveKeys(keys: KeyResponse) {
        with(preferences.edit()) {
            putString(AUTH_KEY, "${keys.authKey}")
            putString(SUGGESTION_KEY, "${keys.suggestionKey}")
            putString(TIPS_KEY, "${keys.tipsKey}")
            putString(SURVEY_KEY, "${keys.authKey}")
            commit()
        }
    }

    override fun getCredentials(): String = preferences.getString(AUTHORIZATION, "") ?: ""

    override fun hasValidCredentials() = preferences.getString(AUTHORIZATION, "")?.isNotEmpty() ?: false

    override fun getApiKey(key: String) = preferences.getString(key, "") ?: ""
}