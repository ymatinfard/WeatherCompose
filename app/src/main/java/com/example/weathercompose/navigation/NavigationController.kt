import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.theme.screen.WeatherAboutScreen
import com.example.weathercompose.ui.theme.screen.WeatherFavorites
import com.example.weathercompose.ui.screen.WeatherHomeScreen
import com.example.weathercompose.ui.theme.screen.WeatherSearchScreen
import com.example.weathercompose.ui.theme.screen.WeatherSettingsScreen
import com.example.weathercompose.ui.screen.WeatherSplashScreen
import com.example.weathercompose.ui.theme.viewmodel.WeatherViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {

        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreens.MainScreen.name) {
            val viewModel = hiltViewModel<WeatherViewModel>()
            WeatherHomeScreen(navController = navController, viewModel)
        }

        composable(WeatherScreens.SearchScreen.name) {
            WeatherSearchScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            WeatherAboutScreen()
        }

        composable(WeatherScreens.SettingsScreen.name) {
            WeatherSettingsScreen()
        }

        composable(WeatherScreens.FavoriteScreen.name) {
            WeatherFavorites()
        }
    }
}