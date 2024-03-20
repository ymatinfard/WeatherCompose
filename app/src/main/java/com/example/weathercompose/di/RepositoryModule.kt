package com.example.weathercompose.di

import com.example.weathercompose.data.WeatherRepository
import com.example.weathercompose.data.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository
}