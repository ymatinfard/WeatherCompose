package com.example.weathercompose.data

import com.example.weathercompose.model.FavoriteModel
import com.example.weathercompose.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(query: String? = null): Flow<WeatherResult<WeatherModel>>
    fun getFavorites(): Flow<List<FavoriteModel>>
    suspend fun saveFavorite(city: String)
}