package com.example.pokecardapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardapp.PokemonSetListFragmentDirections
import com.example.pokecardapp.data.pokeset.PokemonSet
import com.example.pokecardapp.databinding.ItemPokemonSetBinding


class PokemonSetAdapter(private val onItemClicked: (PokemonSet) -> Unit) :
    ListAdapter<PokemonSet, PokemonSetAdapter.PokemonSetViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSetViewHolder {
        return PokemonSetViewHolder(ItemPokemonSetBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PokemonSetViewHolder, position: Int) {
        val pokemonset = getItem(position)
        // onClick navigate to set detail page
        holder.itemView.setOnClickListener {
            onItemClicked(pokemonset)
        }
        holder.bind(pokemonset)
    }

    class PokemonSetViewHolder(private var binding: ItemPokemonSetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemonSet: PokemonSet) {
            binding.pokemonSet = pokemonSet
            binding.tvTotal.text = "0 / " + pokemonSet.total
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonSet>() {
        override fun areItemsTheSame(oldItem: PokemonSet, newItem: PokemonSet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonSet, newItem: PokemonSet): Boolean {
            return oldItem.images.logo == oldItem.images.logo
        }

    }
}
