package com.example.pokecardapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.network.PokemonApi
import kotlinx.coroutines.launch

private const val TAG = "PokemonViewModel"

class PokemonViewModel : ViewModel() {

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    fun getCardsFromSet(id: String) {
        try {
            viewModelScope.launch {
                _pokemons.value = PokemonApi.retrofitService.getCardsFromSet(id).data
                Log.i(TAG, "onSuccess, data: ${_pokemons.value}")
                Log.i(TAG, "onSuccess, data: ${_pokemons.value?.size}")
                Log.i(TAG, "onSuccess, id: $id")
            }
        } catch (e: Exception) {
            Log.i(TAG, "onFailure, from getting cards from set")
        }
    }
}

// TODO: pass in dao
/**
 * Creates the PokemonViewModel instance
 * @param = Set ID, to get data from that set
 */
//class PokemonViewModelFactory(private val id: String) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return PokemonViewModel(id) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
