package com.example.smsrly.utility

fun formatPrice(price: Double): String {
    return when {
        price >= 1_000_000_000 -> String.format("%.1fB", price / 1_000_000_000)
        price >= 1_000_000 -> String.format("%.1fM", price / 1_000_000)
        price >= 1_000 -> String.format("%.1fK", price / 1_000)
        else -> String.format("%.0f", price)
    }
}