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

// Hilt-moduuli, joka tarjoaa verkkoon liittyvät riippuvuudet: Gson, Retrofit ja RestaurantApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Tarjoaa Gson-instanssin JSON-serialisointiin ja -deserialisointiin
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    // Tarjoaa Retrofit-instanssin, joka käyttää paikallista emulaattoripalvelinta (http://10.0.2.2)
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/") // Android-emulaattorin yhteys isäntäkoneen localhostiin
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // Tarjoaa RestaurantApi-rajapinnan, joka toteutetaan Retrofitin avulla
    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): RestaurantApi =
        retrofit.create(RestaurantApi::class.java)
}