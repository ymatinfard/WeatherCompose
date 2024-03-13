package com.example.weathercompose.model

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName("list")
    val list: List<MainEntity>? = null,
) {
    fun toDomain() = WeatherModel(list?.map(MainEntity::toDomain))
}

data class MainEntity(
    @SerializedName("dt")
    val dateTime: Long,
    @SerializedName("weather")
    val weather: List<ContentEntity>? = null,
) {
    fun toDomain() = Main(dateTime, weather?.map(ContentEntity::toDomain))
}

data class ContentEntity(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
) {
    fun toDomain(): Content = Content(id, main, description, icon)
}