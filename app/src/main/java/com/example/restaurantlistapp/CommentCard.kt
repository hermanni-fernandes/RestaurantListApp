package com.example.restaurantlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Komponentti, joka esittää yksittäisen käyttäjän kommentin korttimuodossa
@Composable
fun CommentCard(comment: Comment, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp) // Väliä muihin elementteihin
            .fillMaxWidth() // Kortti täyttää koko vaakasuoran tilan
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Row {
                // Tähtiarviointi, käytetään yhteistä RatingStars-komponenttia
                RatingStars(rating = comment.rating.toDouble(), reviewCount = 0)

                Spacer(modifier = Modifier.weight(1f)) // Työntää poistopainikkeen oikealle

                // Kommentin poistopainike
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Poista kommentti" // Ruudunlukuohjelmaa varten
                    )
                }
            }

            // Näytetään kommenttiteksti, jos se ei ole tyhjä
            if (comment.text.isNotBlank()) {
                Text(comment.text)
            }

            Spacer(modifier = Modifier.height(4.dp)) // Pieni väli

            // Näytetään kommentin päivämäärä harmaalla pienellä tekstillä
            Text(
                "Päiväys: ${comment.date}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
