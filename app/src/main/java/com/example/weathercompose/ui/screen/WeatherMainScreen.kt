package com.example.weathercompose.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weathercompose.R
import com.example.weathercompose.model.WeatherModel
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.viewmodel.WeatherUIState
import com.example.weathercompose.ui.viewmodel.WeatherViewModel
import com.example.weathercompose.utils.formatDate
import com.example.weathercompose.widgets.DailyItemRow
import com.example.weathercompose.widgets.HumidityWindPressureRow
import com.example.weathercompose.widgets.SunsetSunRiseRow
import com.example.weathercompose.widgets.TopCircle
import com.example.weathercompose.widgets.WeatherAppBar

@Composable
fun WeatherHomeScreen(navController: NavController, viewModel: WeatherViewModel, city: String) {

    viewModel.getWeather(city)
    val weatherInfo by viewModel.weatherInfo.collectAsState()
    when (weatherInfo) {
        is WeatherUIState.Success -> {
            HomeScaffold(
                weatherInfo = (weatherInfo as WeatherUIState.Success).data,
                navController = navController,
                city,
            )
        }

        is WeatherUIState.Loading -> {

        }

        is WeatherUIState.Error -> {

        }
    }
}

@Composable
fun HomeScaffold(weatherInfo: WeatherModel, navController: NavController?, city: String) {
    Scaffold(
        topBar = {
            if (navController != null) {
                WeatherAppBar(title = city, isMainScreen = true, onAddButtonClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                }, navController = navController)
            }
        },
    ) { innerPadding ->
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatDate(weatherInfo.dt),
                Modifier.padding(innerPadding)
            )
            TopCircle()
            HumidityWindPressureRow(weatherInfo.main)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp), color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(6.dp))
            SunsetSunRiseRow(weatherInfo)
            Text(text = stringResource(id = R.string.this_week), Modifier.padding(top = 10.dp))
            // TODO() There is no proper data, so I repeat the same data to create a list to show in main screen
            val weatherList = listOf(weatherInfo, weatherInfo, weatherInfo)
            LazyColumn {
                items(weatherList) {
                    DailyItemRow(it)
                }
            }
        }
    }
}
