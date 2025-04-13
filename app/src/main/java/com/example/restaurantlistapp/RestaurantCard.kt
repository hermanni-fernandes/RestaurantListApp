package com.example.restaurantlistapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// Kortti, joka näyttää ravintolan tiedot
@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {

            // Ravintolan kuva
            Image(
                painter = painterResource(R.drawable.burger),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {

                // Nimi
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Tähtiarvio ja arvioiden määrä
                RatingStars(
                    rating = restaurant.rating,
                    reviewCount = restaurant.reviewCount
                )

                // Tyyppi ja hintaluokka
                Text("${restaurant.type} • ${restaurant.priceRange}")

                // Osoite
                Text(restaurant.address)

                // Avoinna / sulkeutumassa -teksti
                Text(
                    text = if (restaurant.isOpen) "Open" else "Closing soon",
                    color = if (restaurant.isOpen) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// Tähtirivi + arvioiden määrä
@Composable
fun RatingStars(rating: Double, reviewCount: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = if (index < rating.toInt()) Color(0xFFFFC107) else Color.LightGray,
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text("$rating ($reviewCount)")
    }
}
