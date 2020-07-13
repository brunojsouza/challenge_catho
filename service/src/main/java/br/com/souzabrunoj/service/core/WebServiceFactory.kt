package br.com.souzabrunoj.service.core

import br.com.souzabrunoj.service.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebServiceFactory {

    inline fun <reified T> createWebService(url: String, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(T::class.java)
    }

    fun createOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient = OkHttpClient.Builder()
        .dispatcher(Dispatcher().apply { maxRequests = 1 })
        .addInterceptor(createHttpLoggingInterceptor())
        .addInterceptor(requestInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun createHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .dispatcher(Dispatcher().apply { maxRequests = 1 })
        .addInterceptor(createHttpLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}