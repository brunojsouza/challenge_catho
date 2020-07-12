package br.com.souzabrunoj.domain.common

import android.content.Context
import androidx.annotation.StringRes
import org.koin.core.KoinComponent
import org.koin.core.get

object KoinInjector : KoinComponent

fun getStringFromResources(@StringRes id: Int): String {
    return try {
        KoinInjector.get<Context>().getString(id)
    } catch (error: Throwable) {
        ""
    }
}