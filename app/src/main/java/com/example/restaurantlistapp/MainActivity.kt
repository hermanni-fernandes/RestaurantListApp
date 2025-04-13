@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class) // Salli Material3:n kokeelliset ominaisuudet

package com.example.restaurantlistapp

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import androidx.navigation.compose.*
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme

// Ravintolatietojen tietoluokka
data class Restaurant(
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceRange: String,
    val address: String,
    val isOpen: Boolean
)

// Mallidataa näytettäväksi listalla
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

        // Käynnistetään Compose UI
        setContent {
            RestaurantListAppTheme {
                val navController = rememberNavController()

                // NavHost hallitsee siirtymiä näkymien välillä
                NavHost(
                    navController = navController,
                    startDestination = "restaurantList"
                ) {
                    // Ravintolalistan näkymä
                    composable("restaurantList") {
                        RestaurantListScreen(navController)
                    }

                    // Kommenttisivu, parametrina ravintolan nimi
                    composable(
                        route = "comments/{restaurantName}",
                        arguments = listOf(navArgument("restaurantName") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("restaurantName") ?: ""
                        CommentScreen(restaurantName = name, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            // Näytetään yläpalkki sovelluksen otsikolla
            TopAppBar(
                title = { Text("Restaurants") }
            )
        }
    ) { padding ->
        // Listaus kaikista ravintoloista LazyColumnissa
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Jokaiselle ravintolalle oma kortti
            items(sampleRestaurants) { restaurant ->
                RestaurantCard(restaurant) {
                    // Navigoi kommenttinäkymään ravintolan nimeä käyttäen
                    navController.navigate("comments/${restaurant.name}")
                }
            }
        }
    }
}

@Composable
fun RestaurantCard(restaurant: Restaurant, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }, // Koko kortti on klikattavissa
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            // Hampurilaiskuva vasemmalla
            Image(
                painter = painterResource(R.drawable.burger),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Ravintolan nimi otsikkotyylillä
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis // Jos nimi on liian pitkä
                )

                // Tähtiarvio + lukumäärä
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

                // Ravintolan tyyppi ja hintaluokka
                Text("${restaurant.type} • ${restaurant.priceRange}")

                // Osoite
                Text(restaurant.address)

                // Avoinna / Sulkeutumassa
                Text(
                    text = if (restaurant.isOpen) "Open" else "Closing soon",
                    color = if (restaurant.isOpen) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
