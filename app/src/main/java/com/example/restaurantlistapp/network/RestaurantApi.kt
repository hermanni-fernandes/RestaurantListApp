package com.example.restaurantlistapp.network

import com.example.restaurantlistapp.Restaurant
import retrofit2.http.GET

// API-rajapinta, joka määrittää miten ravintolat haetaan REST-palvelimelta
interface RestaurantApi {

    // Hakee kaikki ravintolat
    @GET("/restaurants")
    suspend fun getRestaurants(): List<Restaurant>
}