package com.sampleandroidarchitecture.di

import com.sampleandroidarchitecture.ui.screens.city_search.CitySearchViewModel
import com.sampleandroidarchitecture.ui.screens.city_weather.CityWeatherViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { CitySearchViewModel(get(), get()) }
    factory { params -> CityWeatherViewModel(params[0], get()) }
}