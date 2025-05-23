package com.example.restaurantlistapp.network

import com.example.restaurantlistapp.model.RestaurantDto
import retrofit2.http.GET

// REST API -rajapinta ravintoloiden hakemista varten
interface RestaurantApi {
    @GET("restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>
}
