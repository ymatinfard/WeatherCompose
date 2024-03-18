package com.example.weathercompose.data.remote

import android.util.Log
import com.example.weathercompose.data.WeatherRepository
import com.example.weathercompose.data.WeatherResult
import com.example.weathercompose.di.Dispatcher
import com.example.weathercompose.di.WeatherDispatchers
import com.example.weathercompose.model.WeatherModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class OnlineWeatherRepository @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: WeatherApi
) : WeatherRepository {
    override fun getWeather(query: String?): Flow<WeatherResult<WeatherModel>> =
        flow<WeatherResult<WeatherModel>> {
            val res = api.getWeather(query)
            emit(
                WeatherResult.Success(data = res.toDomain())
            )
        }.catch {
            emit(WeatherResult.Error(it))
        }.flowOn(ioDispatcher)
}