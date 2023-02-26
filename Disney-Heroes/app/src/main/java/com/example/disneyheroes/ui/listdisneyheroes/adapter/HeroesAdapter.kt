package com.example.disneyheroes.ui.listdisneyheroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.disneyheroes.databinding.ItemHeroBinding
import com.example.disneyheroes.models.DisneyHero

class HeroesAdapter(
    private val onClickHero: (hero: DisneyHero) -> Unit
) :
    PagingDataAdapter<DisneyHero, HeroesViewHolder>(HeroesUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        getItem(position)?.let { hero ->
            holder.bind(hero)
            holder.itemView.setOnClickListener {
                onClickHero(hero)
            }
        }
    }
}