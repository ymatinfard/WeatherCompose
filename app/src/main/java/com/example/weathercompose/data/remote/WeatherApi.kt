package com.example.weathercompose.data.remote

import com.example.weathercompose.model.WeatherEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET(value = "weather?appid=b2baa5591288db3453c105260ad01a8d")
    suspend fun getWeather(@Query("q") city: String? = "messina"): WeatherEntity
}