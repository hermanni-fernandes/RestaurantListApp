package com.example.restaurantlistapp.di

import com.example.restaurantlistapp.network.RestaurantApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Hilt-moduuli, joka tarjoaa Retrofitin ja API-rajapinnan injektointia varten
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Tarjoaa Gson-objektin
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    // Tarjoaa Retrofit-instanssin, joka käyttää emulaattorin localhostia (http://10.0.2.2)
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/") // Paikallinen backend Android-emulaattorille
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // Tarjoaa RestaurantApi-rajapinnan Retrofitin avulla
    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)
}
