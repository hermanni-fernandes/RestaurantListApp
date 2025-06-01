package com.example.restaurantlistapp.model

// Tietoluokka, joka vastaa REST API:n palauttamaa ravintoladataa
data class RestaurantDto(
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceRange: String,
    val address: String,
    val isOpen: Boolean
)