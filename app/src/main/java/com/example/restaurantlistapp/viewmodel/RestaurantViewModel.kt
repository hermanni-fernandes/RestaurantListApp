package com.example.restaurantlistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantlistapp.Comment
import com.example.restaurantlistapp.Restaurant
import com.example.restaurantlistapp.network.RestaurantApi
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// ViewModel, joka hakee ravintolatiedot REST-rajapinnasta
@HiltViewModel
class RestaurantViewModel @Inject constructor(private val api: RestaurantApi) : ViewModel() {

    // Ravintolalistan tila
    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    // Kommenttilista (paikallinen, ei backendistä tässä vaiheessa)
    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments

    // Virhetilojen hallinta
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Hakee ravintolatiedot backendista
    fun fetchRestaurants() {
        viewModelScope.launch {
            try {
                val response = api.getRestaurants()

                _restaurants.value = response
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Virhe haettaessa tietoja: ${e.localizedMessage}"
            }
        }
    }

    // Lisää kommentti listaan
    fun addComment(comment: Comment) {
        _comments.value = _comments.value + comment
    }

    // Poistaa kommentin listasta
    fun deleteComment(comment: Comment) {
        _comments.value = _comments.value - comment
    }
}