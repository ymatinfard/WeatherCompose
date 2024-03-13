package com.example.weathercompose.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun WeatherFavorites() {
    Text(text = "Favorites page", modifier = Modifier.background(color = MaterialTheme.colorScheme.primary))
}