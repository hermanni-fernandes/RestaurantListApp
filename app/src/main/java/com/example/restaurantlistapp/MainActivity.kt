package com.example.restaurantlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme
import com.example.restaurantlistapp.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    // Haetaan ravintolatiedot kun composable käynnistyy
    LaunchedEffect(Unit) {
        viewModel.fetchRestaurants()
    }

    NavHost(
        navController = navController,
        startDestination = "restaurantList"
    ) {
        composable("restaurantList") {
            RestaurantListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "comment/{restaurantName}",
            arguments = listOf(navArgument("restaurantName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("restaurantName") ?: ""
            CommentScreen(
                restaurantName = name,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
