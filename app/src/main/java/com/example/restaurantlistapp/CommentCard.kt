package com.example.restaurantlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CommentCard(comment: Comment, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Row {
                // Käytetään yhteistä tähtikomponenttia
                RatingStars(rating = comment.rating.toDouble(), reviewCount = 0)

                Spacer(modifier = Modifier.weight(1f))

                // Poistopainike
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Poista kommentti"
                    )
                }
            }

            // Kommenttiteksti, jos ei tyhjä
            if (comment.text.isNotBlank()) {
                Text(comment.text)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Päivämäärä pienellä tekstillä
            Text(
                "Päiväys: ${comment.date}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
