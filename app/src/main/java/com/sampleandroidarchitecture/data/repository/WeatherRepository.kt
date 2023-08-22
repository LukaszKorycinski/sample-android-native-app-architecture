package com.sampleandroidarchitecture.data.repository

import com.sampleandroidarchitecture.data.api.SafeResponse
import com.sampleandroidarchitecture.data.api.clients.WeatherApiClient
import com.sampleandroidarchitecture.data.api.models.response.CityResponseItem
import com.sampleandroidarchitecture.data.api.models.response.ForecastResponse
import com.sampleandroidarchitecture.data.api.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository(
    private val weatherApiClient: WeatherApiClient,
): WeatherRepositoryInterface {

    override suspend fun getCityAutocomplete(query: String): Flow<SafeResponse<List<CityResponseItem>>> =
        flow {
            emit(safeApiCall {
                weatherApiClient.getCityAutocomplete(
                    query = query,
                )
            })
        }

    override suspend fun getForecast(locationKey: String): Flow<SafeResponse<ForecastResponse>> =
        flow {
            emit(safeApiCall {
                weatherApiClient.getForecast(
                    locationKey = locationKey,
                )
            })
    }
}