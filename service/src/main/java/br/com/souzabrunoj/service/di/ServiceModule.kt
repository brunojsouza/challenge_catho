package br.com.souzabrunoj.service.di

import br.com.souzabrunoj.service.BuildConfig
import br.com.souzabrunoj.service.common.SafeResponse
import br.com.souzabrunoj.service.core.RequestInterceptor
import br.com.souzabrunoj.service.core.WebServiceFactory
import br.com.souzabrunoj.service.networking.Networking
import br.com.souzabrunoj.service.networking.NetworkingImpl
import br.com.souzabrunoj.service.networking.WebService
import br.com.souzabrunoj.service.networking.data.local.PreferenceServiceImpl
import br.com.souzabrunoj.service.networking.data.local.PreferencesService
import org.koin.dsl.module

val serviceModule = module {
    single { SafeResponse() }
    single<PreferencesService> { PreferenceServiceImpl(context = get()) }
    single { RequestInterceptor(get()) }
    single { WebServiceFactory.createOkHttpClient(get()) }
    single<WebService> { WebServiceFactory.createWebService(BuildConfig.BASE_URL, get()) }
    single<Networking> { NetworkingImpl(get()) }
}