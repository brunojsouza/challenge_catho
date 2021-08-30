package br.com.souzabrunoj.service.di

import br.com.souzabrunoj.domain.repository.Repository
import br.com.souzabrunoj.service.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get(), get()) }
}