package com.example.pokecardapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pokecardapp.adapter.CardListAdapter
import com.example.pokecardapp.adapter.PokemonAdapter
import com.example.pokecardapp.data.pokemon.CardMarket
import com.example.pokecardapp.data.pokemon.CardPrice
import com.example.pokecardapp.data.pokemon.PokeImage
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.databinding.FragmentPokemonSearchBinding
import com.example.pokecardapp.viewmodels.SearchViewModel
import com.example.pokecardapp.viewmodels.SearchViewModelFactory

class PokemonSearchFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels {
        SearchViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPokemonSearchBinding.inflate(inflater)

        val adapter = PokemonAdapter { pokemon ->
            val action =
                PokemonSearchFragmentDirections.actionPokemonSearchFragmentToCardFragment(
                    pokemon = pokemon,
                    name = pokemon.name
                )
            // pass in custom object or add each name, id, etc manually
            findNavController().navigate(action)
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvPokemons.adapter = adapter

        binding.svPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("SearchFragment", "$query")
                if (query != null) {
                    viewModel.getCardsByName("name:$query")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("SearchFragment", "Typing")
                return false
            }
        })

        return binding.root
    }
}