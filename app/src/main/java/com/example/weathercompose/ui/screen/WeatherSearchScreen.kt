package com.example.weathercompose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weathercompose.R
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.viewmodel.WeatherViewModel
import com.example.weathercompose.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherSearchScreen(viewModel: WeatherViewModel, navController: NavController) {

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Search page",
                isMainScreen = false,
                icon = ImageVector.vectorResource(
                    id = R.drawable.arrow_back_24
                ),
                onButtonClicked = {
                    navController.popBackStack()
                },
                viewModel = viewModel,
                navController = navController
            )
        }
    ) {
        SearchBar(modifier = Modifier.padding(it), onSearch = { city ->
            navController.navigate(WeatherScreens.MainScreen.name + "/$city")
        })
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier, onSearch: (String) -> Unit) {
    val searchStr = rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = searchStr.value,
        onValueChange = {
            searchStr.value = it
        },
        label = { Text("Search me") },
        maxLines = 1,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Blue, cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        keyboardActions = KeyboardActions {
            if (searchStr.value.isEmpty().not()) {
                onSearch.invoke(searchStr.value)
            }
        }
    )
}