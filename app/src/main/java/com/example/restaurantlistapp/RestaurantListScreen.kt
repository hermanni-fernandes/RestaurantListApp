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
    viewModel: RestaurantViewModel = hiltViewModel() // ViewModel injektoidaan Hiltin avulla
) {
    // Haetaan ravintolat vain kerran composablen käynnistyessä
    LaunchedEffect(Unit) {
        viewModel.fetchRestaurants()
    }

    // Seurataan ravintolalistan ja virheviestin tilaa
    val restaurantList by viewModel.restaurants.collectAsState()
    val error by viewModel.error.collectAsState()

    // Sovelluksen perusrakenne (yläpalkki ja sisältö)
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ravintolat") }) // Näytetään sovelluksen otsikko yläpalkissa
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {

            // Jos virhe on olemassa, näytetään se punaisena tekstinä
            error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Näytetään ravintolat LazyColumnissa
            LazyColumn {
                items(restaurantList) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onClick = {
                            // Navigoidaan kommenttinäkymään ravintolan nimen perusteella
                            navController.navigate("comment/${restaurant.name}")
                        }
                    )
                }
            }
        }
    }
}
