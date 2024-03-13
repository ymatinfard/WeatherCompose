package com.example.weathercompose.data

sealed interface WeatherResult<out T> {
    data object Loading : WeatherResult<Nothing>
    data class Success<T>(val data: T): WeatherResult<T>
    data class Error(val exception: Throwable? = null) : WeatherResult<Nothing>
}