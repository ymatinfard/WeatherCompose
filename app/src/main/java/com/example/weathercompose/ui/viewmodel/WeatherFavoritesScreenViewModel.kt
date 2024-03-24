package com.example.weathercompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercompose.data.WeatherRepository
import com.example.weathercompose.model.FavoriteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherFavoritesScreenViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {

    val favorites: StateFlow<List<FavoriteModel>> = repository.getFavorites().stateIn(
        scope = viewModelScope,
        initialValue = emptyList(),
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    fun deleteFavorite(favorite: FavoriteModel) {
        viewModelScope.launch {
            repository.removeFavoriteCity(favorite)
        }
    }
}