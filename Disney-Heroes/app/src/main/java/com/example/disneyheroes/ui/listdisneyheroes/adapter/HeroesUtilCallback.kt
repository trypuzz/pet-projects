package com.example.disneyheroes.ui.listdisneyheroes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.disneyheroes.models.DisneyHero

class HeroesUtilCallback : DiffUtil.ItemCallback<DisneyHero>() {

    override fun areItemsTheSame(oldItem: DisneyHero, newItem: DisneyHero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DisneyHero, newItem: DisneyHero): Boolean {
        return oldItem == newItem
    }
}