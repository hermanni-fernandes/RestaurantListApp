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

@Composable
fun CommentScreen(
    restaurant: Restaurant,
    navController: NavHostController,
    viewModel: RestaurantViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CommentTopBar(restaurant.name) {
                navController.navigateUp()
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {

            // Yläosan ravintolatiedot
            RestaurantCard(restaurant = restaurant, onClick = null)

            // Kommenttiotsikko
            Text(
                text = "Kommentit: ${restaurant.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Kommenttilista
            CommentList(viewModel)
        }

        // Dialogi uuden kommentin lisäämiseen
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

@Composable
private fun CommentTopBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Takaisin")
            }
        }
    )
}

@Composable
private fun CommentList(viewModel: RestaurantViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.comments) { comment ->
            CommentCard(
                comment = comment,
                onDelete = { viewModel.deleteComment(comment) }
            )
        }
    }
}
