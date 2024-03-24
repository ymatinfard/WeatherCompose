import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weathercompose.navigation.WeatherScreens
import com.example.weathercompose.ui.screen.WeatherFavoritesScreen
import com.example.weathercompose.ui.screen.WeatherHomeScreen
import com.example.weathercompose.ui.screen.WeatherSettingsScreen
import com.example.weathercompose.ui.screen.WeatherSplashScreen
import com.example.weathercompose.ui.theme.screen.WeatherAboutScreen
import com.example.weathercompose.ui.screen.WeatherSearchScreen
import com.example.weathercompose.ui.viewmodel.WeatherFavoritesScreenViewModel
import com.example.weathercompose.ui.viewmodel.WeatherViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {

        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreens.MainScreen.name + "/{city}", arguments = listOf(
            navArgument(name = "city") {
                type = NavType.StringType
            }
        )) { navBack ->
            val city = navBack.arguments?.getString("city") ?: "Messina"
            val viewModel = hiltViewModel<WeatherViewModel>()
            WeatherHomeScreen(navController = navController, viewModel, city)
        }

        composable(WeatherScreens.SearchScreen.name) {
            val viewModel = hiltViewModel<WeatherViewModel>()
            WeatherSearchScreen(viewModel = viewModel, navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            WeatherAboutScreen()
        }

        composable(WeatherScreens.SettingsScreen.name) {
            WeatherSettingsScreen()
        }

        composable(WeatherScreens.FavoriteScreen.name) {
            val viewModel = hiltViewModel<WeatherFavoritesScreenViewModel>()
            WeatherFavoritesScreen(viewModel)
        }
    }
}