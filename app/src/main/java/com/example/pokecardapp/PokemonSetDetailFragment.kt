package com.example.pokecardapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokecardapp.adapter.PokemonAdapter
import com.example.pokecardapp.databinding.FragmentPokemonSetDetailBinding
import com.example.pokecardapp.viewmodels.PokemonViewModel
import com.example.pokecardapp.viewmodels.PokemonViewModelFactory

class PokemonSetDetailFragment : Fragment() {

    private val navigationArgs: PokemonSetDetailFragmentArgs by navArgs()
    private val viewModel: PokemonViewModel by activityViewModels {
        PokemonViewModelFactory("set.id:${navigationArgs.setId}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPokemonSetDetailBinding.inflate(inflater)

        val adapter = PokemonAdapter { pokemon ->
            val action =
                PokemonSetDetailFragmentDirections.actionPokemonSetDetailFragmentToCardFragment()
            // pass in custom object or add each name, id, etc manually
            findNavController().navigate(action)
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvCards.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}