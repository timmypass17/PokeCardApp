package com.example.pokecardapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pokecardapp.adapter.CardListAdapter
import com.example.pokecardapp.data.CardApplication
import com.example.pokecardapp.data.pokemon.CardMarket
import com.example.pokecardapp.data.pokemon.CardPrice
import com.example.pokecardapp.data.pokemon.PokeImage
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.databinding.FragmentPokemonListBinding
import com.example.pokecardapp.viewmodels.CardViewModel
import com.example.pokecardapp.viewmodels.CardViewModelFactory

class PokemonListFragment : Fragment() {

    private val viewModel: CardViewModel by activityViewModels {
        CardViewModelFactory(
            (activity?.application as CardApplication).database.cardDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPokemonListBinding.inflate(inflater)

        val adapter = CardListAdapter { card ->
            // Need to create pokemon to pass into card fragment
            val pokemonImage = PokeImage(small = card.cardImage, large = card.cardImage)
            val cardPrice = CardPrice(averageSellPrice = card.price)
            val cardMarket = CardMarket(updatedAt = card.updatedAt, prices = cardPrice)
            val pokemon = Pokemon(
                id = card.id,
                name = card.cardName,
                images = pokemonImage,
                cardmarket = cardMarket)

            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToCardFragment(
                    pokemon = pokemon,
                    name = pokemon.name
                )
            findNavController().navigate(action)
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvCards.adapter = adapter

        return binding.root
    }
}