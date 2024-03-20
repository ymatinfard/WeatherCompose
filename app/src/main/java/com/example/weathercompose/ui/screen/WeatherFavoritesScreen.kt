package com.example.weathercompose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weathercompose.model.FavoriteModel
import com.example.weathercompose.ui.viewmodel.WeatherFavoritesScreenViewModel


@Composable
fun WeatherFavoritesScreen(viewModel: WeatherFavoritesScreenViewModel) {
    val favorites by viewModel.favorites.collectAsState()
    LazyColumn {
        items(favorites) { favorite ->
            WeatherFavoriteRow(favorite) {
                viewModel.deleteFavorite(favorite)
            }
        }
    }
}

@Preview
@Composable
fun WeatherFavoriteRow(
    favoriteModel: FavoriteModel = FavoriteModel("Messi"),
    onDelete: (FavoriteModel) -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(6.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(text = favoriteModel.city)
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.clickable {
                    onDelete.invoke(favoriteModel)
                })
        }
    }
}