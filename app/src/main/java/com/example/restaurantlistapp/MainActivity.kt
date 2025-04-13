package com.example.restaurantlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.*
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Asetetaan sovelluksen teema ja käynnistetään navigaatio
            RestaurantListAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Navigaatiorakenne (NavHost)
    NavHost(
        navController = navController,
        startDestination = "restaurantList" // Aloitusnäkymä
    ) {
        // Reitti ravintolalistaan
        composable("restaurantList") {
            RestaurantListScreen(navController)
        }

        // Reitti kommenttinäkymään, parametrina ravintolan nimi
        composable(
            route = "comment/{restaurantName}",
            arguments = listOf(navArgument("restaurantName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("restaurantName")
            // Etsitään ravintola nimen perusteella
            val restaurant = sampleRestaurants.find { it.name == name }
            if (restaurant != null) {
                // Näytetään kommenttinäkymä valitulle ravintolalle
                CommentScreen(restaurant = restaurant, navController = navController)
            }
        }
    }
}
