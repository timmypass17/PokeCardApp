package com.example.pokecardapp.data.pokemon

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class PokemonResponse(
    val data: List<Pokemon>
)

@Parcelize
data class Pokemon(
    val id: String,
    val name: String,
    val images: PokeImage,
    var cardmarket: CardMarket = CardMarket(updatedAt = "none", prices = CardPrice(averageSellPrice = "0.00"))
) : Parcelable

@Parcelize
data class PokeImage(
    val small: String,
    val large: String
) : Parcelable

@Parcelize
data class CardMarket(
    val updatedAt: String,
    val prices: CardPrice
) : Parcelable

@Parcelize
data class CardPrice(
    val averageSellPrice: String?
) : Parcelable

