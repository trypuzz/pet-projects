package com.example.disneyheroes.ui.disneyhero.infoheroadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.disneyheroes.models.InfoHero

class InfoHeroUtilCallback : DiffUtil.ItemCallback<InfoHero>() {
    override fun areItemsTheSame(oldItem: InfoHero, newItem: InfoHero): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: InfoHero, newItem: InfoHero): Boolean {
        return false
    }
}