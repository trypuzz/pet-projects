package com.example.disneyheroes.ui.listdisneyheroes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyheroes.databinding.ItemHeroBinding
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.utils.loadUrl

class FavoriteHeroesAdapter(
    private val onClickHero: (hero: DisneyHero) -> Unit
) :
    RecyclerView.Adapter<FavoriteHeroesAdapter.FavoriteHeroesViewHolder>() {

    private var listFavoriteHeroes = emptyList<DisneyHero>()

    inner class FavoriteHeroesViewHolder(val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHeroesViewHolder {
        return FavoriteHeroesViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteHeroesViewHolder, position: Int) {
        holder.binding.textNameHero.text = listFavoriteHeroes[position].name
        holder.binding.imageHero.loadUrl(listFavoriteHeroes[position].imageUrl)
        holder.itemView.setOnClickListener {
            onClickHero(listFavoriteHeroes[position])
        }
    }

    override fun getItemCount(): Int {
        return listFavoriteHeroes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListFavoriteHero(list: List<DisneyHero>) {
        listFavoriteHeroes = list
        notifyDataSetChanged()
    }
}