package com.sampleandroidarchitecture.data.repository

import com.sampleandroidarchitecture.data.api.SafeResponse
import com.sampleandroidarchitecture.data.api.models.response.CityResponseItem
import com.sampleandroidarchitecture.data.api.models.response.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepositoryInterface {
    suspend fun getCityAutocomplete(query: String): Flow<SafeResponse<List<CityResponseItem>>>

    suspend fun getForecast(locationKey: String): Flow<SafeResponse<ForecastResponse>>
}