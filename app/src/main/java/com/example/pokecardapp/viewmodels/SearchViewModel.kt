package com.example.pokecardapp.viewmodels

import androidx.lifecycle.*
import com.example.pokecardapp.data.CardDao
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.network.PokemonApi
import kotlinx.coroutines.launch

class SearchViewModel() : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> = _pokemons


    init {
        // https://api.pokemontcg.io/v2/cards?q=name:charizard
        getCardsByName("name:pikachu")   // default init
    }

    fun getCardsByName(name: String) {
        _status.value = PokemonApiStatus.LOADING
        try {
            viewModelScope.launch {
                _pokemons.value = PokemonApi.retrofitService.getCardsWithName(name).data
                _status.value = PokemonApiStatus.DONE
            }
        } catch (e: Exception) {
            _pokemons.value = listOf()
            _status.value = PokemonApiStatus.ERROR
        }
    }
}

/**
 * Creates ViewModel instance
 */
class SearchViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}