package br.com.souzabrunoj.challenge_catho.application

import android.app.Application
import br.com.souzabrunoj.challenge_catho.di.presentationModules
import br.com.souzabrunoj.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@AppApplication)
            modules(appModules.plus(presentationModules))
        }
    }
}