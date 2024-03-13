package com.example.weathercompose.data.remote

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

class WeatherRepository @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val dataSource: WeatherNetworkDataSource
) {
    fun getWeather(): Flow<WeatherResult<WeatherModel>> = flow<WeatherResult<WeatherModel>> {
        emit(
            WeatherResult.Success(data = dataSource.getWeatherInfo().toDomain())
        )
    }.catch {
        emit(WeatherResult.Error(it))
    }.flowOn(ioDispatcher)
}