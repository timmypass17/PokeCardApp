package com.example.pokecardapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val cardName: String,
    @ColumnInfo(name = "image") val cardImage: String
)