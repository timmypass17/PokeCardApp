package com.example.pokecardapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokecardapp.adapter.PokemonSetAdapter
import com.example.pokecardapp.databinding.FragmentPokemonSetListBinding
import com.example.pokecardapp.viewmodels.SetViewModel

class PokemonSetListFragment : Fragment() {

    private val viewModel: SetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonSetListBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel // first time use viewmodel, calls init()

        // Initialize the RecyclerView adapter in rvPokemonSets to a new PhotoGridAdapter object
        val adapter = PokemonSetAdapter { pokemonSet ->
            val action =
                PokemonSetListFragmentDirections.actionPokemonSetListFragmentToPokemonSetDetailFragment(
                    pokemonSet,
                    pokemonSet.name
                )
            findNavController().navigate(action)
        }
        binding.rvPokemonSets.adapter = adapter

        return binding.root
    }

}