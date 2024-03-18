package com.example.weathercompose.model

data class WeatherModel(
    val main: MainModel,
    val dt: Long,
    val sys: SysModel,
    val name: String
)

data class SysModel(
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class MainModel(
    val temp: Float,
    val feelsLike: Float,
    val tempMin: Float,
    val tempMax: Float,
    val pressure: Int,
    val humidity: Int,
)