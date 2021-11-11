package com.example.pokecardapp.data.pokemon

data class PokemonResponse(
    val data: List<Pokemon>
)

data class Pokemon(
    val id: String,
    val name: String,
    val images: PokeImage
)

data class PokeImage(
    val small: String
)
