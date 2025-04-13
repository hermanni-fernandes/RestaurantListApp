@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Näytettävät ravintolat
val sampleRestaurants = listOf(
    Restaurant(
        name = "Mahtava ravintola",
        rating = 4.8,
        reviewCount = 3,
        type = "Italian",
        priceRange = "$$",
        address = "Jokivylä 11 C, 96300 ROVANIEMI",
        isOpen = true
    ),
    Restaurant(
        name = "Kova",
        rating = 0.0,
        reviewCount = 0,
        type = "Suomalaista grillimättöä",
        priceRange = "$",
        address = "Grillikyljenkatu 5, 96100 ROVANIEMI",
        isOpen = false
    )
)

@Composable
fun RestaurantListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ravintolat") }) // Sovelluksen yläpalkki
        }
    ) { padding ->
        // Lista ravintoloista
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(sampleRestaurants) { restaurant ->
                RestaurantCard(
                    restaurant = restaurant,
                    onClick = {
                        // Navigoi kommenttisivulle ravintolan nimellä
                        navController.navigate("comment/${restaurant.name}")
                    }
                )
            }
        }
    }
}
