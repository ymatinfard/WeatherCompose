package com.example.weathercompose.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weathercompose.R
import com.example.weathercompose.model.MainModel
import com.example.weathercompose.model.WeatherModel
import com.example.weathercompose.utils.formatDate
import com.example.weathercompose.utils.formatTime


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