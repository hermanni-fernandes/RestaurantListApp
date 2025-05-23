@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.restaurantlistapp.viewmodel.RestaurantViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CommentScreen(
    restaurant: Restaurant,
    navController: NavHostController,
    viewModel: RestaurantViewModel = hiltViewModel() // ✅ Hiltin avulla jaettu ViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    // Kerätään kommentit StateFlow:sta
    val comments by viewModel.comments.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = restaurant.name) }, // Näytetään ravintolan nimi
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Takaisin")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+") // Kommentin lisäysnäppäin
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Ravintolan tiedot
            RestaurantCard(restaurant = restaurant, onClick = null)

            Text(
                text = "Kommentit: ${restaurant.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Näytetään kommentit
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(comments) { comment ->
                    CommentCard(
                        comment = comment,
                        onDelete = { viewModel.deleteComment(comment) }
                    )
                }
            }
        }

        // Kommentin lisäysdialogi
        if (showDialog) {
            AddCommentDialog(
                onSubmit = { rating, text ->
                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                    viewModel.addComment(Comment(rating = rating, text = text, date = date))
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}
