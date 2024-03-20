package com.example.weathercompose.data.fake

import com.example.weathercompose.data.WeatherRepository
import com.example.weathercompose.data.WeatherResult
import com.example.weathercompose.di.Dispatcher
import com.example.weathercompose.di.WeatherDispatchers
import com.example.weathercompose.model.FavoriteModel
import com.example.weathercompose.model.WeatherModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeWeatherRepository(
    @Dispatcher(WeatherDispatchers.IO) val ioDispatcher: CoroutineDispatcher,
    private val dataSource: FakeWeatherNetworkDataSource
) : WeatherRepository {
    override fun getWeather(query: String?) = flow<WeatherResult<WeatherModel>> {
        emit(WeatherResult.Success(dataSource.getWeatherInfo().toDomain()))
    }.catch {
        emit(WeatherResult.Error(it))
    }.flowOn(ioDispatcher)

    override fun getFavorites(): Flow<List<FavoriteModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavorite(city: String) {
        TODO("Not yet implemented")
    }
}