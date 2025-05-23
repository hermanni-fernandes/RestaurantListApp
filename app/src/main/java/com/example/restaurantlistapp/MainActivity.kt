package com.example.restaurantlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme
import com.example.restaurantlistapp.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantListAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: RestaurantViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "restaurantList") {
        composable("restaurantList") {
            RestaurantListScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = "comment/{restaurantName}",
            arguments = listOf(navArgument("restaurantName") { type = NavType.StringType })
        ) { backStackEntry ->
            val restaurantName = backStackEntry.arguments?.getString("restaurantName")
            val restaurant = viewModel.restaurants.value.find { it.name == restaurantName }
            if (restaurant != null) {
                CommentScreen(restaurant = restaurant, navController = navController, viewModel = viewModel)
            }
        }
    }
}
