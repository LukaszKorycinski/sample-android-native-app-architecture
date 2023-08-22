package com.sampleandroidarchitecture.di

import com.sampleandroidarchitecture.data.repository.LocalRepository
import com.sampleandroidarchitecture.data.repository.WeatherRepository
import com.sampleandroidarchitecture.data.repository.WeatherRepositoryInterface
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepositoryInterface> { WeatherRepository(get()) } bind WeatherRepositoryInterface::class
    single { LocalRepository(get()) }
}