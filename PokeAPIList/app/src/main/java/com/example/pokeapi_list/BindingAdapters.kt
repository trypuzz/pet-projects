package com.example.pokeapi_list

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter
import com.example.pokeapi_list.dataclasses.Pokemon

import com.example.pokeapi_list.ui.PokemonApiStatus
import com.example.pokeapi_list.ui.PokemonListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Pokemon>?) {
    //val adapter = recyclerView.adapter as PokemonListAdapter
    //adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: PokemonApiStatus?) {
    when(status) {
        PokemonApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PokemonApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        PokemonApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {}
    }
}