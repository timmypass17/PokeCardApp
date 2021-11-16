package com.example.pokecardapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.data.pokeset.PokemonSet
import com.example.pokecardapp.data.pokeset.PokemonSetResponse
import com.example.pokecardapp.network.PokemonApi
import kotlinx.coroutines.launch

enum class PokemonApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [PokemonSetListFragment].
 */

private const val TAG = "SetViewModel"

class SetViewModel : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemonSets = MutableLiveData<List<PokemonSet>>()
    val pokemonSets: LiveData<List<PokemonSet>> = _pokemonSets

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    init {
        getSetsData() // populate viewmodel with retrofit data
    }

    private fun getSetsData() {
        _status.value = PokemonApiStatus.LOADING
        try {
            viewModelScope.launch {
                _pokemonSets.value = PokemonApi.retrofitService.getSets().data // pass in List<pokeset>
                _status.value = PokemonApiStatus.DONE
            }
        } catch (e: Exception) {
            _pokemonSets.value = listOf()   // set list to empty if we failed to get data
            _status.value = PokemonApiStatus.ERROR
        }
    }

}