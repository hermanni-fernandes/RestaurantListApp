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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantListScreen(
    navController: NavHostController,
    viewModel: RestaurantViewModel = hiltViewModel() // 🔹 Käytetään Hiltin tarjoamaa ViewModelia
) {
    // 🔹 Haetaan ravintolat vain kerran composablen käynnistyessä
    LaunchedEffect(Unit) {
        viewModel.fetchRestaurants()
    }

    val restaurantList by viewModel.restaurants.collectAsState()
    val error by viewModel.error.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ravintolat") }) // Sovelluksen yläpalkki
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // 🔹 Näytetään virheilmoitus, jos sellainen on
            error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn {
                items(restaurantList) { restaurant ->
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
