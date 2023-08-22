package com.sampleandroidarchitecture.di

import com.sampleandroidarchitecture.data.api.clients.WeatherApiClient
import org.koin.dsl.module

internal val apiModule = module {
    single { retrofitService<WeatherApiClient>(get()) }
}