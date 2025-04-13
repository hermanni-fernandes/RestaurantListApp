package com.example.restaurantlistapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class RestaurantViewModel : ViewModel() {

    // Kommenttilista tallennetaan tilana
    val comments = mutableStateListOf<Comment>()

    // Lisää uusi kommentti
    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    // Poistaa kommentin
    fun deleteComment(comment: Comment) {
        comments.remove(comment)
    }
}
