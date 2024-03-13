package com.example.weathercompose.model

data class Main(
    val dateTime: Long,
    val weather: List<Content>? = null,
)

data class Content(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)

data class WeatherModel(
    val weatherInfo: List<Main>? = emptyList(),
)