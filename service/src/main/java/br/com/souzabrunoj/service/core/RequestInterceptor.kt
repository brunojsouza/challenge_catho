package br.com.souzabrunoj.service.core

import br.com.souzabrunoj.domain.common.AUTHORIZATION
import br.com.souzabrunoj.domain.common.X_API_KEY
import br.com.souzabrunoj.service.networking.data.local.PreferencesService
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor(private val preferencesService: PreferencesService) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        return if (preferencesService.hasValidCredentials()) {
            val credentials = preferencesService.getCredentials()
            chain.proceed(
                request.newBuilder()
                    .header(AUTHORIZATION, credentials.first)
                    .header(X_API_KEY, credentials.second)
                    .build()
            )
        } else chain.proceed(request)
    }
}
