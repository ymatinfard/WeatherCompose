package com.example.weathercompose.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.weathercompose.ui.viewmodel.WeatherFavoritesScreenViewModel


@Composable
fun WeatherFavoritesScreen(viewModel: WeatherFavoritesScreenViewModel) {
    Text(text = "Favorites page", modifier = Modifier.background(color = MaterialTheme.colorScheme.primary))
}