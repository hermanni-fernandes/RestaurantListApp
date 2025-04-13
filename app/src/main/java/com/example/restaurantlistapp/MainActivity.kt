@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme

data class Restaurant(
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceRange: String,
    val address: String,
    val isOpen: Boolean
)

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantListAppTheme {
                RestaurantListScreen()
            }
        }
    }
}

@Composable
fun RestaurantListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurants") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(sampleRestaurants) { restaurant ->
                RestaurantCard(restaurant)
            }
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                Toast.makeText(context, "Clicked: ${restaurant.name}", Toast.LENGTH_SHORT).show()
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            // Kuvan näyttö resurssista (burger.png)
            Image(
                painter = painterResource(R.drawable.burger),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Nimi otsikkona
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Tähtirivistö ja arvosana
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = if (index < restaurant.rating.toInt()) Color(0xFFFFC107) else Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${restaurant.rating} (${restaurant.reviewCount})")
                }

                // Tyyppi ja hintaluokka
                Text("${restaurant.type} • ${restaurant.priceRange}")

                // Osoite
                Text(restaurant.address)

                // Aukiolo / Sulkeutumassa
                Text(
                    text = if (restaurant.isOpen) "Open" else "Closing soon",
                    color = if (restaurant.isOpen) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
