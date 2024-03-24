package com.example.weathercompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class WeatherEntity(
    val main: MainEntity,
    val dt: Long,
    val sys: SysEntity,
    val name: String
) {
    fun toDomain(): WeatherModel = WeatherModel(main.toDomain(), dt, sys.toDomain(), name)
}

data class SysEntity(
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
) {
    fun toDomain(): SysModel = SysModel(type, id, country, sunrise, sunset)
}

data class MainEntity(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int,
) {
    fun toDomain(): MainModel = MainModel(temp, feels_like, temp_min, temp_max, pressure, humidity)
}

@Entity(tableName = "favorite_tbl")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String
) {
    fun toDomain() = FavoriteModel(city)
}