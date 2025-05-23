package com.example.restaurantlistapp.data

import com.example.restaurantlistapp.Restaurant
import com.example.restaurantlistapp.network.RestaurantApi
import javax.inject.Inject

// Repository, joka vastaa tietojen hakemisesta API:sta
class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi
) {
    // Hakee kaikki ravintolat API:sta
    suspend fun getAllRestaurants(): List<Restaurant> {
        return api.getRestaurants()
    }
}
