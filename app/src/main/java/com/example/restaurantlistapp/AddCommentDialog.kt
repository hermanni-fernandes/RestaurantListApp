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
    onSubmit: (Float, String) -> Unit, // Funktio, joka kutsutaan kun kommentti lähetetään
    onDismiss: () -> Unit              // Funktio, joka kutsutaan kun dialogi suljetaan
) {
    // Muistetaan tähditys ja tekstikentän sisältö
    var rating by remember { mutableStateOf(0f) }
    var commentText by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = onDismiss, // Sulje dialogi kun taustaa painetaan
        confirmButton = {
            TextButton(onClick = {
                onSubmit(rating, commentText.text) // Lähetä arvosana ja teksti
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
                Text("Arviosi:")
                Row {
                    // Tähtien näyttö ja klikkaus
                    for (i in 1..5) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = if (i <= rating) Color(0xFFFFC107) else Color.LightGray,
                            modifier = Modifier
                                .padding(4.dp)
                                .size(32.dp)
                                .clickable { rating = i.toFloat() } // Aseta arvosana
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text("Kommenttisi:")
                TextField(
                    value = commentText,
                    onValueChange = { commentText = it }, // Päivitä tekstikentän sisältö
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
