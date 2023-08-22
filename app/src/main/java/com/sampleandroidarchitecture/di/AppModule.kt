package com.sampleandroidarchitecture.di

import com.sampleandroidarchitecture.data.data_store.DataStore
import org.koin.dsl.module

internal val appModule = module {
    single { DataStore(get(), get()) }
}