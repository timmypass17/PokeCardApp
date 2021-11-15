package com.example.pokecardapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokecardapp.data.Card
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.databinding.FragmentPokemonListBinding
import com.example.pokecardapp.databinding.ItemCardBinding

class CardListAdapter(private val onItemClicked: (Card) -> Unit) :
        ListAdapter<Card, CardListAdapter.CardViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(card)
        }
        holder.bind(card)
    }

    class CardViewHolder(private var binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.card = card
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.cardImage == oldItem.cardImage
        }

    }

}