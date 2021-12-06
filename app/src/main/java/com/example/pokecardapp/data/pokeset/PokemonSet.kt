package com.example.pokecardapp.data.pokeset

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PokemonSetResponse(
    val data: List<PokemonSet>
)

@Parcelize
data class PokemonSet(
    val id: String,
    val name: String,
    val images: PokeSetImage,
    val total: String
) : Parcelable

@Parcelize
data class PokeSetImage (
    val logo: String,
    val symbol: String
) : Parcelable