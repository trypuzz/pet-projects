package com.example.pokeapi_list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.pokeapi_list.databinding.ListViewItemBinding
import com.example.pokeapi_list.network.Pokemon

class PokemonListAdapter(private val clickListener: PokemonListener) :
    ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    class PokemonViewHolder(var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PokemonListener, pokemon: Pokemon) {
            binding.pokemon = pokemon
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.type == newItem.type && oldItem.description == newItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(clickListener, pokemon)
    }
}

class PokemonListener(val clickListener: (pokemon: Pokemon) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon)
}