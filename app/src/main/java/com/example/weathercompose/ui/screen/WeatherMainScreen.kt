package com.example.weathercompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.weathercompose.R
import com.example.weathercompose.model.MainModel
import com.example.weathercompose.model.WeatherModel
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.viewmodel.WeatherViewModel
import com.example.weathercompose.ui.viewmodel.WeatherUIState
import com.example.weathercompose.utils.formatDate
import com.example.weathercompose.utils.formatTime
import com.example.weathercompose.widgets.WeatherAppBar

@Composable
fun WeatherHomeScreen(navController: NavController, viewModel: WeatherViewModel) {

    val weatherInfo by viewModel.weatherInfo.collectAsState()
    when (weatherInfo) {
        is WeatherUIState.Success -> {
            HomeScaffold(
                weatherInfo = (weatherInfo as WeatherUIState.Success).data,
                navController = navController
            )
        }

        is WeatherUIState.Loading -> {

        }

        is WeatherUIState.Error -> {

        }
    }
}

@Composable
fun HomeScaffold(weatherInfo: WeatherModel, navController: NavController?) {
    Scaffold(
        topBar = {
            if (navController != null) {
                WeatherAppBar(title = "Messina", isMainScreen = true, onAddButtonClicked = {
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

@Composable
fun SunsetSunRiseRow(sunInfo: WeatherModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.sun_rise2),
                contentDescription = "Sun rise icon"
            )
            Text(text = formatTime(sunInfo.sys.sunrise))
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.sun_set2),
                contentDescription = "Sun set icon"
            )
            Text(text = formatTime(sunInfo.sys.sunset))
        }
    }
}

@Composable
fun HumidityWindPressureRow(weatherDetail: MainModel) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                painter = painterResource(R.drawable.humidity),
                contentDescription = "Humidity icon"
            )
            Text(text = "${weatherDetail.humidity} %", style = MaterialTheme.typography.labelSmall)
        }

        Row {
            Icon(
                painter = painterResource(R.drawable.pressure),
                contentDescription = "Pressure icon"
            )
            Text(text = "${weatherDetail.pressure} %", style = MaterialTheme.typography.labelSmall)
        }

        Row {
            Icon(painter = painterResource(R.drawable.wind), contentDescription = "Wind icon")
            Text(text = "wind %", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun TopCircle() {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(200.dp),
        color = Color.Yellow,
        shape = CircleShape,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherStateImage(res = R.drawable.weather_cloudy)
            // Load image online
            //   WeatherStateImage(imageUrl = "https://openweathermap.org/img/wn/10d.png")
            Text(text = "54" + "º", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Rain")
        }
    }
}

@Composable
fun WeatherStateImage(res: Int) {
    Surface(modifier = Modifier.wrapContentSize(), color = Color.Transparent) {
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(res)),
            modifier = Modifier.size(32.dp), contentDescription = null
        )
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun WeatherCondition() {
    Surface(shape = RoundedCornerShape(6.dp), color = Color.Yellow) {
        Text(text = "light rain", modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun HighLowTemperature(temp: MainModel) {
    Row {
        Text(text = "${temp.tempMax}°", color = Color.Blue)
        Text(text = "${temp.tempMin}°", color = Color.Gray)
    }
}

@Composable
fun DailyItemRow(weatherInfo: WeatherModel) {
    Surface(
        shape = RoundedCornerShape(topStart = 32.dp, bottomStart = 32.dp, bottomEnd = 32.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = formatDate(weatherInfo.dt).substringBefore(","))
            WeatherStateImage(imageUrl = "image_url")
            WeatherCondition()
            HighLowTemperature(weatherInfo.main)
        }
    }
}
