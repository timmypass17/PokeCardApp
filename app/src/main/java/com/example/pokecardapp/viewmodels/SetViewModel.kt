package com.example.pokecardapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokecardapp.data.pokeset.PokemonSet
import com.example.pokecardapp.data.pokeset.PokemonSetResponse
import com.example.pokecardapp.network.PokemonApi
import kotlinx.coroutines.launch

enum class PokemonApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [PokemonSetListFragment].
 */
class SetViewModel : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemonSets = MutableLiveData<List<PokemonSet>>()
    val pokemonSets: LiveData<List<PokemonSet>> = _pokemonSets

    init {
        getSetsData() // populate viewmodel with retrofit data
    }

    fun getSetsData() {
        try {
            viewModelScope.launch {
                _pokemonSets.value = PokemonApi.retrofitService.getSets().data // pass in List<pokeset>
                _status.value = PokemonApiStatus.DONE
            }
        } catch (e: Exception) {
            _status.value = PokemonApiStatus.ERROR
        }
    }
}