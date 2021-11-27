package com.example.pokecardapp.viewmodels

import androidx.lifecycle.*
import com.example.pokecardapp.data.Card
import com.example.pokecardapp.data.CardDao
import com.example.pokecardapp.network.PokemonApi
import com.example.pokecardapp.utilities.getFormattedPrice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat

class CardViewModel(private val cardDao: CardDao) : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private var _allCards: Flow<List<Card>> = cardDao.getCards()
    val allCards: LiveData<List<Card>> = _allCards.asLiveData()

    private var _totalPrice: Flow<Double> = cardDao.getTotalPrice()
//    val totalPrice: LiveData<Double> = _totalPrice.asLiveData()
    val totalPrice: LiveData<String> = Transformations.map(_totalPrice.asLiveData()) { price -> getFormattedPrice(price) }

    private var _totalCount: Flow<Int> = cardDao.getCardAmount()
    val totalCount: LiveData<Int> =  _totalCount.asLiveData()

    init {
        // update room with new api data

        // Use work manager?
    }

    fun addCard(card: Card) {
        viewModelScope.launch {
            cardDao.insert(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch {
            cardDao.delete(card)
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