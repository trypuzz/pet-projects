package com.example.disneyheroes.ui.disneyhero.infoheroadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.disneyheroes.databinding.ItemListInfoBinding
import com.example.disneyheroes.models.InfoHero

class InfoHeroAdapter : ListAdapter<InfoHero, InfoHeroViewHolder>(InfoHeroUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHeroViewHolder {
        return InfoHeroViewHolder(
            ItemListInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: InfoHeroViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}