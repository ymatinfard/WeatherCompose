package com.example.weathercompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercompose.data.remote.OnlineWeatherRepository
import com.example.weathercompose.data.WeatherResult
import com.example.weathercompose.model.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: OnlineWeatherRepository) :
    ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val weatherInfo : StateFlow<WeatherUIState> = repository.getWeather("london").flatMapLatest {
        flowOf (
            when (it) {
                is WeatherResult.Loading -> WeatherUIState.Loading
                is WeatherResult.Success<WeatherModel> -> WeatherUIState.Success(it.data)
                is WeatherResult.Error -> WeatherUIState.Error(it.exception)
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = WeatherUIState.Loading,
    )

    init {
      //  Log.d("Yousef", "getWeather called viewmodel")
      //  getWeather()
    }


//    fun getWeather() {
//        viewModelScope.launch {
//            val res = repository.getWeather()
//            when (res) {
//                is WeatherResponse.Success<*> -> {
//
//                }
//            }
//        }
//    }
}

sealed interface WeatherUIState {
    data object Loading : WeatherUIState
    data class Success(val data: WeatherModel) : WeatherUIState
    data class Error(val e: Throwable?) : WeatherUIState
}

