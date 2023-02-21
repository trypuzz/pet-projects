package com.example.disneyheroes.ui.listdisneyheroes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.disneyheroes.databinding.ItemHeroBinding
import com.example.disneyheroes.models.DisneyHero
import com.example.disneyheroes.utils.loadUrl

class HeroesViewHolder(
    private val binding: ItemHeroBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hero: DisneyHero) {
        binding.apply {
            textNameHero.text = hero.name
            imageHero.loadUrl(hero.imageUrl)
        }
    }
}