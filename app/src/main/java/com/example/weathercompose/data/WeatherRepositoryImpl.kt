package com.example.weathercompose.data

import com.example.weathercompose.data.local.dao.WeatherDao
import com.example.weathercompose.data.remote.WeatherApi
import com.example.weathercompose.di.Dispatcher
import com.example.weathercompose.di.WeatherDispatchers
import com.example.weathercompose.model.FavoriteEntity
import com.example.weathercompose.model.FavoriteModel
import com.example.weathercompose.model.WeatherModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class WeatherRepositoryImpl @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val api: WeatherApi,
    private val dao: WeatherDao
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

    override fun getFavorites(): Flow<List<FavoriteModel>> = dao.getFavorites().map {
        it.map(FavoriteEntity::toDomain)
    }

    override suspend fun saveFavorite(city: String) {
        withContext(ioDispatcher) {
            dao.saveFavorite(FavoriteEntity(city))
        }
    }
}