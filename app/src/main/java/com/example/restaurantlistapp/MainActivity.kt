package com.example.restaurantlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme

// Ravintoladata-luokka
data class Restaurant(
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceRange: String,
    val address: String
)

// Mallidataa - 2 ravintolaa
val sampleRestaurants = listOf(
    Restaurant(
        name = "Mahtava ravintola",
        rating = 4.8,
        reviewCount = 3,
        type = "Italialainen",
        priceRange = "$$",
        address = "Jokivylä 11 C, 96300 Rovaniemi"
    ),
    Restaurant(
        name = "Kova",
        rating = 3.2,
        reviewCount = 14,
        type = "Sushi",
        priceRange = "$$$",
        address = "Sushikatu 5, 00100 Helsinki"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantListAppTheme {
                Surface {
                    // Näytetään lista ravintoloista
                    RestaurantList(restaurants = sampleRestaurants)
                }
            }
        }
    }
}

@Composable
fun RestaurantList(restaurants: List<Restaurant>) {
    // Scrollattava lista (LazyColumn)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Jokaiselle ravintolalle kutsutaan korttifunktio
        items(restaurants) { restaurant ->
            RestaurantCard(restaurant)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant) {
    // Näytetään yksittäisen ravintolan tiedot
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Nimi otsikkona
        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis // ... jos nimi liian pitkä
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Arvosana: ${restaurant.rating}")
        Text(text = "Arviointeja: ${restaurant.reviewCount}")
        Text(text = "Tyyppi: ${restaurant.type}")
        Text(text = "Hintaluokka: ${restaurant.priceRange}")
        Text(text = "Osoite: ${restaurant.address}")
    }
}
