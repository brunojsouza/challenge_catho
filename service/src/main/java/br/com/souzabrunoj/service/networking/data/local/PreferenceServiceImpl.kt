package br.com.souzabrunoj.service.networking.data.local

import android.content.Context
import android.content.SharedPreferences
import br.com.souzabrunoj.domain.common.AUTHORIZATION
import br.com.souzabrunoj.domain.common.PREFERENCE_KEY
import br.com.souzabrunoj.domain.common.X_API_KEY
import br.com.souzabrunoj.domain.data.response.login.LoginModel

class PreferenceServiceImpl(private val context: Context) : PreferencesService {
    private val preferences: SharedPreferences by lazy { context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE) }

    private fun saveCredentials(userToken: String?, apiKey: String?) = with(preferences.edit()) {
        putString(AUTHORIZATION, "$userToken")
        putString(X_API_KEY, " $apiKey")
        commit()
    }

    override fun saveUser(user: LoginModel) {
        with(preferences.edit()) {
            saveCredentials(user.token, user.id)
            commit()
        }
    }

    override fun getCredentials(): Pair<String, String> =
         Pair(preferences.getString(AUTHORIZATION, "") ?: "", preferences.getString(X_API_KEY, "") ?: "")

    override fun hasValidCredentials() = preferences.getString(AUTHORIZATION, "")?.isNotEmpty() ?: false
}