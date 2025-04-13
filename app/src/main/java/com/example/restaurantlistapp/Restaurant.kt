package com.example.restaurantlistapp

// Ravintolan tiedot
data class Restaurant(
    val name: String,
    val rating: Double,
    val reviewCount: Int,
    val type: String,
    val priceRange: String,
    val address: String,
    val isOpen: Boolean
)
