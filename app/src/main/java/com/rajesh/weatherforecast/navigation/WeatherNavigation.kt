package com.rajesh.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rajesh.weatherforecast.screens.about.AboutScreen
import com.rajesh.weatherforecast.screens.favourites.FavouritesScreen
import com.rajesh.weatherforecast.screens.main.MainScreen
import com.rajesh.weatherforecast.screens.main.MainViewModel
import com.rajesh.weatherforecast.screens.search.SearchScreen
import com.rajesh.weatherforecast.screens.settings.SettingsScreen
import com.rajesh.weatherforecast.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(navArgument(name = "city") {
                type = NavType.StringType
            })) { navBack ->
            navBack.arguments?.getString("city").let {city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel,city = city)

            }
        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name) {
            FavouritesScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }

}



