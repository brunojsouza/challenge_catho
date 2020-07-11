package br.com.souzabrunoj.di

import br.com.souzabrunoj.repository.di.repositoryModule
import br.com.souzabrunoj.service.di.serviceModule

val appModules = listOf(repositoryModule, serviceModule)