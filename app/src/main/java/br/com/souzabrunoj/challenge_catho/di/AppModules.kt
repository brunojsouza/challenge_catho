package br.com.souzabrunoj.challenge_catho.di

import br.com.souzabrunoj.challenge_catho.presentation.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module{
    viewModel { HomeViewModel(get()) }
}
