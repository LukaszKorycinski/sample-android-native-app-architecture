package com.sampleandroidarchitecture.data.repository

import com.sampleandroidarchitecture.data.api.models.response.CityResponseItem
import com.sampleandroidarchitecture.data.data_store.DataStore

class LocalRepository(
    private val dataStore: DataStore,
) {
    suspend fun getCities() = dataStore.loadCities()

    suspend fun saveCity(city: CityResponseItem){
        val savedCities = getCities().toMutableList()
        savedCities.removeAll { it.key == city.key }
        savedCities.add(0, city)
        dataStore.saveCities(savedCities)
    }
}