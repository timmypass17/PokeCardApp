package com.example.pokecardapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardapp.data.pokeset.PokemonSet
import com.example.pokecardapp.databinding.ItemPokemonSetBinding


class PokemonSetAdapter : ListAdapter<PokemonSet,
        PokemonSetAdapter.PokemonViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonSetBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonset = getItem(position)
        holder.bind(pokemonset)
    }

    class PokemonViewHolder(private var binding: ItemPokemonSetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonSet: PokemonSet) {
            binding.pokemonSet = pokemonSet
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonSet>() {
        override fun areItemsTheSame(oldItem: PokemonSet, newItem: PokemonSet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonSet, newItem: PokemonSet): Boolean {
            return oldItem.images.symbol == oldItem.images.symbol
        }

    }
}
