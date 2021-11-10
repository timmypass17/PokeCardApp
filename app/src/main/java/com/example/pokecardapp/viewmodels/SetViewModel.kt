package com.example.pokecardapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokecardapp.network.PokemonApi
import kotlinx.coroutines.launch

enum class PokemonApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [PokemonSetListFragment].
 */
class SetViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<PokemonApiStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<PokemonApiStatus> = _status

    init {
        getSetsData()
    }

    fun getSetsData() {
        try {
            viewModelScope.launch {
                val listResult = PokemonApi.retrofitService.getSets()
                _status.value = PokemonApiStatus.DONE
                Log.i("ViewModel", "OnSucess: $listResult")
            }
        } catch (e: Exception) {
            _status.value = PokemonApiStatus.ERROR
            Log.i("ViewModel", "Error")
        }
    }
}