@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.restaurantlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CommentScreen(
    restaurantName: String,
    navController: NavHostController
) {
    // Näkymärakenne, jossa yläpalkki ja sisältö
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurantName) }, // Ravintolan nimi yläpalkissa
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        // Takaisin-nuoli
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Takaisin"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Tähän tulee uuden kommentin lisäyslogiikka myöhemmin
            }) {
                Text("+") // Plus-ikoni kommentin lisäämiseksi
            }
        }
    ) { padding ->
        // Pääsisältö
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Väliaikainen teksti kommenttien paikalle
            Text(
                text = "Kommentit ravintolalle: $restaurantName",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
