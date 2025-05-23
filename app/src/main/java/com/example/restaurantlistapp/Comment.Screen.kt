@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

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
    restaurantName: String,
    navController: NavHostController,
    viewModel: RestaurantViewModel = hiltViewModel() // Shared ViewModel injektoituna
) {
    val restaurantList by viewModel.restaurants.collectAsState()
    val commentList by viewModel.comments.collectAsState() // Collect the comments list
    val restaurant = restaurantList.find { it.name == restaurantName }

    var showDialog by remember { mutableStateOf(false) }

    if (restaurant == null) {
        Text("Ravintolaa ei löytynyt")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = restaurant.name) },
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
            RestaurantCard(restaurant = restaurant, onClick = null)

            Text(
                text = "Kommentit: ${restaurant.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(commentList) { comment -> // Now you're passing the collected list
                    CommentCard(
                        comment = comment,
                        onDelete = { viewModel.deleteComment(comment) }
                    )
                }
            }
        }

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
