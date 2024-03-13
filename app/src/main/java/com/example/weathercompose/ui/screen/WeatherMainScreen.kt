package com.example.weathercompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weathercompose.R
import com.example.weathercompose.model.Content
import com.example.weathercompose.model.WeatherEntity
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.theme.viewmodel.WeatherViewModel
import com.example.weathercompose.data.WeatherResult
import com.example.weathercompose.model.WeatherModel
import com.example.weathercompose.ui.theme.viewmodel.WeatherUIState
import com.example.weathercompose.widgets.WeatherAppBar
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WeatherHomeScreen(navController: NavController, viewModel: WeatherViewModel) {

    val weatherInfo by viewModel.weatherInfo.collectAsState()
    when (weatherInfo) {
        is WeatherUIState.Success -> {
            HomeScaffold(
                content = (weatherInfo as WeatherUIState.Success).data,
                navController = navController
            )
        }

        is WeatherUIState.Loading -> {

        }

        is WeatherUIState.Error -> {

        }

        else -> {}
    }
}

@Composable
fun HomeScaffold(content: WeatherModel, navController: NavController?) {
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
            Text(text = "Wed, January 3", Modifier.padding(innerPadding))
            Surface(
                modifier = Modifier
                    .padding(15.dp)
                    .size(200.dp),
                color = Color.Yellow,
                shape = CircleShape,
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.weather),
//                    modifier = Modifier.size(32.dp),
//                    contentDescription = null
//                )
                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WeatherStateImage(res = R.drawable.weather_cloudy)
                    Text(text = "54" + "º", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Rain")
                }
            }
        }
    }
}

@Composable
fun MainContent(data: Content) {

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

@Preview
@Composable
fun PreviewHome() {
   // HomeScaffold(navController = null)
}

