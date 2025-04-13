package com.example.restaurantlistapp

// Malli yksittäiselle kommentille
data class Comment(
    val rating: Float,    // Tähtiarvosana (1–5)
    val text: String,     // Kommentin sisältö
    val date: String      // Kommentin aikaleima (esim. "2025-04-13 12:30:00")
)
