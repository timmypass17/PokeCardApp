package com.example.pokecardapp.data

import android.app.Application

class CardApplication : Application() {
    val database: CardRoomDatabase by lazy {
        CardRoomDatabase.getDatabase(this)
    }
}