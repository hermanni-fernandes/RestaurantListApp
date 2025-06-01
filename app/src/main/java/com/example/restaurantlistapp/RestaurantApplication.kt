package com.example.restaurantlistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// Määritellään Hiltin käytön aloituskohta
@HiltAndroidApp
class RestaurantApplication : Application()