package com.example.pokecardapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pokecardapp.data.Card
import com.example.pokecardapp.data.CardDao
import com.example.pokecardapp.data.pokemon.Pokemon
import kotlinx.coroutines.launch

class CardViewModel(private val cardDao: CardDao) : ViewModel() {

    fun addCard(card: Card) {
        viewModelScope.launch {
            cardDao.insert(card)
        }
    }
}

/**
 * Creates ViewModel instance
 */
class CardViewModelFactory(private val cardDao: CardDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardViewModel(cardDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}