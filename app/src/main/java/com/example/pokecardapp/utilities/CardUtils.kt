package com.example.pokecardapp.utilities

import java.text.NumberFormat

fun getFormattedPrice(price: Double): String {
    return NumberFormat.getCurrencyInstance().format(price)
}