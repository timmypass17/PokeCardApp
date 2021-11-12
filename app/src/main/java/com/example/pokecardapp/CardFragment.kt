package com.example.pokecardapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.pokecardapp.binding.bindImage
import com.example.pokecardapp.data.Card
import com.example.pokecardapp.data.CardApplication
import com.example.pokecardapp.databinding.FragmentCardBinding
import com.example.pokecardapp.viewmodels.CardViewModel
import com.example.pokecardapp.viewmodels.CardViewModelFactory

class CardFragment : Fragment() {

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CardViewModel by activityViewModels {
        CardViewModelFactory(
            (activity?.application as CardApplication).database.cardDao()
        )
    }
    private val navigationArgs: CardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get pokemon from nav args and convert into Card object to add into Room
        val pokemon = navigationArgs.pokemon
        val card = Card(
            id = pokemon.id,
            cardName = pokemon.name,
            cardImage = pokemon.images.large)

        bindImage(binding.ivCard, pokemon.images.large)
        binding.btnAdd.setOnClickListener { addCard(card) }
    }

    private fun addCard(card: Card) {
        viewModel.addCard(card)
    }
}