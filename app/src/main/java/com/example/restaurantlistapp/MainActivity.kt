package com.example.restaurantlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme
import com.example.restaurantlistapp.viewmodel.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint


// Sovelluksen pääaktiviteetti, johon injektoidaan Hilt-riippuvuudet
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asetetaan sisällöksi Jetpack Compose -pohjainen teema ja navigaatio
        setContent {
            RestaurantListAppTheme {
                AppNavigation()
            }
        }
    }
}

// Sovelluksen navigaatiokomponentti, joka määrittää eri näkymien reitit
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: RestaurantViewModel  = hiltViewModel()// ViewModel injektoituna Hiltin avulla

    // Käynnistetään ravintolatietojen haku heti kun composable käynnistyy
    LaunchedEffect(Unit) {
        viewModel.fetchRestaurants()
    }

    // Määritellään navigaatiorakenne ja näkymien reitit
    NavHost(
        navController = navController,
        startDestination = "restaurantList" // Alkunäkymä
    ) {
        // Reitti ravintolalistalle
        composable("restaurantList") {
            RestaurantListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Reitti yksittäisen ravintolan kommenttisivulle, parametrina ravintolan nimi
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
