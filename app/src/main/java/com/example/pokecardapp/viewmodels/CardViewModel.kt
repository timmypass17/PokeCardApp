package com.example.pokecardapp.viewmodels

import androidx.lifecycle.*
import com.example.pokecardapp.data.Card
import com.example.pokecardapp.data.CardDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CardViewModel(private val cardDao: CardDao) : ViewModel() {

    private val _allCards: Flow<List<Card>> = cardDao.getCards()
    val allCards: LiveData<List<Card>> = _allCards.asLiveData()

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
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardViewModel(cardDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}