package com.example.disneyheroes.ui.disneyhero.nameiteminfoadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.disneyheroes.databinding.ItemNameInfoBinding

class NameItemInfoAdapter : ListAdapter<String, NameItemInfoViewHolder>(NameItemInfoUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameItemInfoViewHolder {
        return NameItemInfoViewHolder(
            ItemNameInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NameItemInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}