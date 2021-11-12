package com.example.pokecardapp.data.pokemon

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class PokemonResponse(
    val data: List<Pokemon>
)

@Parcelize
data class Pokemon(
    val id: String,
    val name: String,
    val images: PokeImage
) : Parcelable

@Parcelize
data class PokeImage(
    val small: String,
    val large: String
) : Parcelable
