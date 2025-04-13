package com.example.restaurantlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.restaurantlistapp.ui.theme.RestaurantListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Käynnistetään Compose-käyttöliittymä
        setContent {
            RestaurantListAppTheme {
                Surface {
                    // Näytetään yksi ravintolakortti
                    RestaurantCard()
                }
            }
        }
    }
}

@Composable
fun RestaurantCard() {
    // Sarake (Column) sijoittaa elementit pystysuunnassa
    Column(
        modifier = Modifier
            .fillMaxWidth()         // vie koko vaakasuoran tilan
            .padding(16.dp)         // ulkoinen marginaali
    ) {
        // Ravintolan nimi, typografia määritetty
        Text(
            text = "Mahtava ravintola",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1, // Näytetään vain yksi rivi
            overflow = TextOverflow.Ellipsis // ... jos nimi on liian pitkä
        )

        Spacer(modifier = Modifier.height(4.dp)) // Väli tilan ja seuraavan tekstin välillä

        // Arvosana numeroarvona
        Text(text = "Arvosana: 4.8")

        // Arvioiden lukumäärä
        Text(text = "Arviointeja: 3")

        // Ravintolan tyyppi
        Text(text = "Tyyppi: Italialainen")

        // Hintaluokka
        Text(text = "Hintaluokka: $$")

        // Osoite
        Text(text = "Osoite: Jokivylä 11 C, 96300 Rovaniemi")
    }
}
