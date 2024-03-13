package com.example.weathercompose.data.fake

import com.example.weathercompose.data.remote.WeatherNetworkDataSource
import com.example.weathercompose.di.Dispatcher
import com.example.weathercompose.di.WeatherDispatchers
import com.example.weathercompose.model.WeatherEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class FakeWeatherNetworkDataSource @Inject constructor(
    @Dispatcher(WeatherDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JVMUnitTestFakeAssetManager()
) : WeatherNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getWeatherInfo(query: String?): WeatherEntity =
        withContext(ioDispatcher) {
            assets.open(WEATHER_INFO).use(networkJson::decodeFromStream)
        }

    companion object {
        const val WEATHER_INFO = "weather_info"
    }
}