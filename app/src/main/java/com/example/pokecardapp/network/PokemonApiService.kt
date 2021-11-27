package com.example.pokecardapp.network

import com.example.pokecardapp.data.pokemon.PokemonResponse
import com.example.pokecardapp.data.pokeset.PokemonSetResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.pokemontcg.io/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokemonApiService  {
    @GET("sets")
    suspend fun getSets(): PokemonSetResponse

    // https://api.pokemontcg.io/v2/cards?q=set.id:mcd21
    @GET("cards")
    suspend fun getCardsFromSet(@Query("q") id: String): PokemonResponse

    @GET("cards")
    suspend fun getCardsWithName(@Query("q") name: String): PokemonResponse
}

/**
 * Singleton, only instanced once, global access.
 * Retrofit.create() is expensive, only needs one instance of Retrofit API service
 */
object PokemonApi {
    val retrofitService : PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}