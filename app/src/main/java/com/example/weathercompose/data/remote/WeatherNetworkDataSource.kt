package com.example.weathercompose.data.remote

import com.example.weathercompose.model.WeatherEntity

interface WeatherNetworkDataSource {
    suspend fun getWeatherInfo(query: String? = null): WeatherEntity
}