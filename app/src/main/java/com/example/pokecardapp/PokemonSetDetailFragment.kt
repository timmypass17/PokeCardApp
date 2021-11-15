package com.example.pokecardapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokecardapp.adapter.PokemonAdapter
import com.example.pokecardapp.databinding.FragmentPokemonSetDetailBinding
import com.example.pokecardapp.viewmodels.PokemonViewModel

class PokemonSetDetailFragment : Fragment() {

    private val navigationArgs: PokemonSetDetailFragmentArgs by navArgs()
    private val viewModel: PokemonViewModel by viewModels()
    private var _binding: FragmentPokemonSetDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPokemonSetDetailBinding.inflate(inflater)

        val adapter = PokemonAdapter { pokemon ->
            val action =
                PokemonSetDetailFragmentDirections.actionPokemonSetDetailFragmentToCardFragment(
                    pokemon = pokemon,
                    name = pokemon.name
                )
            // pass in custom object or add each name, id, etc manually
            findNavController().navigate(action)
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvCards.adapter = adapter

        // TODO: This kinda hackish?
        // we have to put this below adapter for some reason to add different data to viewmodel
        viewModel.getCardsFromSet("set.id:${navigationArgs.setId}") // update with new card data

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}