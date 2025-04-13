@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun CommentScreen(
    restaurantName: String,
    navController: NavHostController,
    viewModel: RestaurantViewModel = viewModel() // Jaettu ViewModel käytössä
) {
    Scaffold(
        topBar = {
            // Yläpalkki ravintolan nimellä ja takaisin-nuolella
            TopAppBar(
                title = { Text(restaurantName) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Takaisin"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            // Plus-painike kommentin lisäämistä varten
            FloatingActionButton(onClick = {
                // Seuraavassa vaiheessa lisätään kommentin lisäysdialogi
            }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Kommenttilistan otsikko
            Text(
                text = "Kommentit: $restaurantName",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Kommentit listana (LazyColumn)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.comments) { comment ->
                    // Yksittäinen kommenttikortti + poistonappi
                    CommentCard(comment = comment, onDelete = {
                        viewModel.deleteComment(comment)
                    })
                }
            }
        }
    }
}

@Composable
fun CommentCard(comment: Comment, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            // Tähtiarvio ja arvosana
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { i ->
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = if (i < comment.rating.toInt()) Color(0xFFFFC107) else Color.LightGray,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = comment.rating.toString())

                Spacer(modifier = Modifier.weight(1f))

                // Roskakori-nappi kommentin poistoon
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Poista kommentti"
                    )
                }
            }

            // Kommentin sisältö (jos on tekstiä)
            if (comment.text.isNotBlank()) {
                Text(comment.text)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Päivämäärä harmaalla tyylillä
            Text(
                "Päiväys: ${comment.date}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
