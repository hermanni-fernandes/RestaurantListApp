package com.example.restaurantlistapp.data


import com.example.restaurantlistapp.Restaurant
import com.example.restaurantlistapp.network.RestaurantApi
import javax.inject.Inject

// Repository vastaa tietojen hakemisesta API:sta
class RestaurantRepository @Inject constructor(
    private val restaurantApi: RestaurantApi
) {
    // Hakee kaikki ravintolat
    suspend fun getAllRestaurants(): List<Restaurant> {
        return restaurantApi.getRestaurants()
    }
}
