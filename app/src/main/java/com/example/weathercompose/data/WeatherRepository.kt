package com.example.weathercompose.data

import com.example.weathercompose.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(query: String? = null): Flow<WeatherResult<WeatherModel>>
}