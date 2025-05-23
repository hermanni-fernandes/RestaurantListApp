package com.example.restaurantlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

// Komponentti, joka näyttää kommentin lisäysdialogin
@Composable
fun AddCommentDialog(
    onSubmit: (Float, String) -> Unit, // Funktio, joka suoritetaan kun käyttäjä lähettää kommentin (arvosana ja teksti)
    onDismiss: () -> Unit              // Funktio, joka suoritetaan kun dialogi suljetaan ilman lähetystä
) {
    var rating by remember { mutableStateOf(0f) } // Käyttäjän valitsema arvosana
    var commentText by remember { mutableStateOf(TextFieldValue("")) } // Käyttäjän syöttämä kommenttiteksti

    AlertDialog(
        onDismissRequest = onDismiss, // Sulje dialogi kun käyttäjä painaa ulkopuolelle tai dismiss-painiketta
        confirmButton = {
            TextButton(onClick = {
                onSubmit(rating, commentText.text) // Lähetetään käyttäjän antamat tiedot
            }) {
                Text("Lähetä")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Peruuta")
            }
        },
        title = { Text("Uusi kommentti") },
        text = {
            Column {
                // Tähtiarviointi (1–5 tähteä)
                StarRatingInput(rating = rating) { rating = it }

                Spacer(modifier = Modifier.height(8.dp))

                // Kommenttikenttä
                Text("Kommenttisi:")
                TextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

// Komponentti, joka näyttää valittavat tähdet arvosanan syöttämistä varten
@Composable
fun StarRatingInput(rating: Float, onRatingChange: (Float) -> Unit) {
    Text("Arviosi:")
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray, // Väri riippuu valinnasta
                modifier = Modifier
                    .padding(4.dp)
                    .size(32.dp)
                    .clickable { onRatingChange(i.toFloat()) } // Päivittää valitun arvosanan
            )
        }
    }
}
