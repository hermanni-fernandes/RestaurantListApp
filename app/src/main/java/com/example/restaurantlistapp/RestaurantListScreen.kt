@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.restaurantlistapp.viewmodel.RestaurantViewModel

@Composable
fun RestaurantListScreen(
    navController: NavHostController,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val restaurants by viewModel.restaurants.collectAsState()
    val error by viewModel.error.collectAsState()

    // Haetaan ravintolat, kun näkymä aukeaa
    LaunchedEffect(Unit) {
        viewModel.fetchRestaurants()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ravintolat") }) // Sovelluksen yläpalkki
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (error != null) {
                Text(
                    text = error ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(restaurants) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onClick = {
                            navController.navigate("comment/${restaurant.name}")
                        }
                    )
                }
            }
        }
    }
}
