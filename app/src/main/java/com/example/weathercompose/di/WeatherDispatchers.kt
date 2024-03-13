package com.example.weathercompose.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val weatherDispatchers: WeatherDispatchers)

enum class WeatherDispatchers {
    Default,
    IO
}