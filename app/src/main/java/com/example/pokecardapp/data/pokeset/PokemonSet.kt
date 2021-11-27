package com.example.pokecardapp.data.pokeset

data class PokemonSetResponse(
    val data: List<PokemonSet>
)

data class PokemonSet(
    val id: String,
    val name: String,
    val images: PokeSetImage,
    val total: String
)

data class PokeSetImage (
    val logo: String
)