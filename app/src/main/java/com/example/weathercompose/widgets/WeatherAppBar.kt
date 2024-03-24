package com.example.weathercompose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    elevation: Dp = 0.dp,
    isMainScreen: Boolean,
    navController: NavController,
    viewModel: WeatherViewModel,
    onAddButtonClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
    onFavoriteClicked: (String) -> Unit = {},
    onRemoveFavoriteClicked: (String) -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }

    val isFavorite = viewModel.isInFavoriteList(title).collectAsState()

    if (showDialog.value) {
        showSettingDropDownMenu(showDialog, navController = navController)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddButtonClicked.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
                IconButton(onClick = {
                    onButtonClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            showDialog.value = true
                        })
                }
            } else {
                Box {}
            }
        },
        navigationIcon = {
            if (isMainScreen) {
                Icon(
                    imageVector = Icons.Default.Favorite, contentDescription = null,
                    modifier = Modifier
                        .padding(3.dp)
                        .clickable {
                            if (isFavorite.value) {
                                onRemoveFavoriteClicked(title)
                            } else {
                                onFavoriteClicked(title)
                            }
                        }, tint = if (isFavorite.value) Color.Red else Color.LightGray
                )
            }
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })
            }
        },
        modifier = Modifier
            .background(Color.Transparent)
            .shadow(elevation = elevation)
            .wrapContentHeight()
    )
}

@Composable
fun showSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }

    val items = listOf("About", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(color = Color.White)
        ) {

            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    text = { Text(text = text, fontWeight = FontWeight.Bold, color = Color.Black) },
                    onClick = {
                        expanded = false
                        showDialog.value = false

                        val route = when (text) {
                            "About" -> WeatherScreens.AboutScreen.name
                            "Favorites" -> WeatherScreens.FavoriteScreen.name
                            else -> WeatherScreens.SettingsScreen.name
                        }

                        navController.navigate(route)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.Favorite
                                else -> Icons.Default.Settings
                            }, tint = Color.LightGray, contentDescription = null
                        )
                    })
            }
        }
    }
}
