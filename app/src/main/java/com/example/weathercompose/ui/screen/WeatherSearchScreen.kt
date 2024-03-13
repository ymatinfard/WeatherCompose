package com.example.weathercompose.ui.theme.screen

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weathercompose.R
import com.example.weathercompose.widgets.WeatherAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherSearchScreen(navController: NavController) {

    Scaffold(
        topBar = {
            WeatherAppBar(title = "Search page",
                isMainScreen = false,
                icon = ImageVector.vectorResource(
                    id = R.drawable.arrow_back_24
                ),
                onButtonClicked = {
                    navController.popBackStack()
                }, navController = navController
            )
        }
    ) {
        SearchBar(modifier = Modifier.padding(it), onSearch = {

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