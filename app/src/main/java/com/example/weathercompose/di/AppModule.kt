package com.example.weathercompose.di

import android.content.Context
import androidx.room.Room
import com.example.weathercompose.data.local.WeatherDatabase
import com.example.weathercompose.data.local.dao.WeatherDao
import com.example.weathercompose.data.remote.WeatherApi
import com.example.weathercompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): WeatherApi = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build().create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather-database"
        ).build()


    @Provides
    @Singleton
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao = database.weatherDao()
}