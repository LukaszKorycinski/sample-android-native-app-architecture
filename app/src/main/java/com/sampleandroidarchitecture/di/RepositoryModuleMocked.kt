package com.sampleandroidarchitecture.di

import com.sampleandroidarchitecture.data.repository.LocalRepository
import com.sampleandroidarchitecture.data.repository.WeatherRepositoryInterface
import com.sampleandroidarchitecture.data.repository.WeatherRepositoryMocked
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModuleMocked = module {
    single<WeatherRepositoryInterface> { WeatherRepositoryMocked() } bind WeatherRepositoryInterface::class
    single { LocalRepository(get()) }
}