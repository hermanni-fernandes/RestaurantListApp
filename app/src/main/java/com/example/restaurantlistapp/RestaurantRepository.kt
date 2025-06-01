package com.example.restaurantlistapp.data

import com.example.restaurantlistapp.Restaurant
import com.example.restaurantlistapp.network.RestaurantApi


// Repository, joka vastaa tietojen hakemisesta API:sta
class RestaurantRepository {
    // Hakee kaikki ravintolat API:sta
    suspend fun getAllRestaurants(): List<Restaurant> {
        return emptyList()
    }
}