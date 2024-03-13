package com.example.weathercompose.data.remote

import com.example.weathercompose.model.WeatherEntity
import com.example.weathercompose.utils.Constants
import retrofit2.http.GET


interface WeatherApi {

    @GET(value = "forecast?id=524901&appid=12943db2bdaa6b0f93d679d790f5e0fa")
    suspend fun getWeather(): WeatherEntity
}