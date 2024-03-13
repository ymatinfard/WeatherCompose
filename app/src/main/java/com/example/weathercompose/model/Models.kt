package com.example.weathercompose.model

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName("list")
    val list: List<Main>? = null) {

    fun toDomain(): WeatherModel {
        return WeatherModel(list.orEmpty())
    }
}

data class Main (
    @SerializedName("weather")
    val weather: List<Content>? = null)

data class Content(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)


data class WeatherModel(
    val weatherInfo: List<Main>
)