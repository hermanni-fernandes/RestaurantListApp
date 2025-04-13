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

@Composable
fun AddCommentDialog(
    onSubmit: (Float, String) -> Unit,
    onDismiss: () -> Unit
) {
    var rating by remember { mutableStateOf(0f) }
    var commentText by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onSubmit(rating, commentText.text)
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
                // Tähtiarvion syöte
                StarRatingInput(rating = rating) { rating = it }

                Spacer(modifier = Modifier.height(8.dp))

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

// Tähtisyöte komponentti (valittavissa 1–5 tähteä)
@Composable
fun StarRatingInput(rating: Float, onRatingChange: (Float) -> Unit) {
    Text("Arviosi:")
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray,
                modifier = Modifier
                    .padding(4.dp)
                    .size(32.dp)
                    .clickable { onRatingChange(i.toFloat()) }
            )
        }
    }
}
