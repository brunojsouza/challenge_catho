package br.com.souzabrunoj.repository.di

import br.com.souzabrunoj.repository.Repository
import br.com.souzabrunoj.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get(), get()) }
}