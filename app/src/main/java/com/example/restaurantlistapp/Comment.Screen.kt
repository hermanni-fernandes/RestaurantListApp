@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Käytetään AutoMirrored-versiota, joka tukee myös RTL-kieliä
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    viewModel: RestaurantViewModel = hiltViewModel() // Injectoitu jaetun ViewModelin instanssi
) {
    // Haetaan ravintolat ja kommentit ViewModelista StateFlow:n kautta
    val restaurantList by viewModel.restaurants.collectAsState()
    val commentList by viewModel.comments.collectAsState()
    val restaurant = restaurantList.find { it.name == restaurantName }

    // Tila, jolla hallitaan lisäysdialogin näkymistä
    var showDialog by remember { mutableStateOf(false) }

    if (restaurant == null) {
        // Näytetään virheilmoitus, jos ravintolaa ei löytynyt
        Text("Ravintolaa ei löytynyt")
        return
    }

    // Päänäkymän rakenne
    Scaffold(
        topBar = {
            // Sovelluksen yläpalkki ravintolan nimellä ja takaisin-napilla
            TopAppBar(
                title = { Text(text = restaurant.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Takaisin"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            // "+" -painike, jolla avataan uuden kommentin lisäysdialogi
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        // Näytetään ravintolan tiedot ja kommenttilista
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Ravintolan tiedot -kortti (ei klikkaustoimintoa)
            RestaurantCard(restaurant = restaurant, onClick = null)

            // Kommenttiosion otsikko
            Text(
                text = "Kommentit: ${restaurant.name}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            // Kommenttilista LazyColumnin avulla
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(commentList) { comment ->
                    CommentCard(
                        comment = comment,
                        onDelete = { viewModel.deleteComment(comment) } // Poistaa kommentin
                    )
                }
            }
        }

        // Näytetään lisäysdialogi, jos tila on päällä
        if (showDialog) {
            AddCommentDialog(
                onSubmit = { rating, text ->
                    // Luodaan päivämäärä ja lähetetään uusi kommentti ViewModeliin
                    val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                    viewModel.addComment(Comment(rating = rating, text = text, date = date))
                    showDialog = false
                },
                onDismiss = { showDialog = false } // Suljetaan dialogi ilman lähetystä
            )
        }
    }
}
