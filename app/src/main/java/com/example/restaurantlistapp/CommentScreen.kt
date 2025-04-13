@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.*

import com.example.restaurantlistapp.Restaurant
import com.example.restaurantlistapp.CommentCard
import com.example.restaurantlistapp.AddCommentDialog
import com.example.restaurantlistapp.RestaurantCard

@Composable
fun CommentScreen(
    restaurant: Restaurant, // Ravintola, jonka kommentit näytetään
    navController: NavHostController,
    viewModel: RestaurantViewModel = viewModel() // Jaettu ViewModel
) {
    var showDialog by remember { mutableStateOf(false) } // Dialogin tila

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = restaurant.name) }, // Näytetään ravintolan nimi yläpalkissa
                navigationIcon = {
                    // Takaisin-nuoli
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
            // Plus-painike kommentin lisäystä varten
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Näytetään ravintolan tiedot (ei klikattava)
            RestaurantCard(restaurant = restaurant, onClick = null)

            // Kommenttien otsikko
            Text(
                text = "Kommentit: ${restaurant.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Lista tallennetuista kommenteista
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.comments) { comment ->
                    CommentCard(
                        comment = comment,
                        onDelete = { viewModel.deleteComment(comment) } // Poista kommentti
                    )
                }
            }
        }

        // Kommentin lisäysdialogi näkyviin
        if (showDialog) {
            AddCommentDialog(
                onSubmit = { rating, text ->
                    // Luo uusi kommentti ja lisää ViewModeliin
                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                    viewModel.addComment(Comment(rating = rating, text = text, date = date))
                    showDialog = false
                },
                onDismiss = { showDialog = false } // Sulje dialogi
            )
        }
    }
}
