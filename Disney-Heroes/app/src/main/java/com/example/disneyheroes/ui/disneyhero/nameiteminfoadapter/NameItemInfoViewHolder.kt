package com.example.disneyheroes.ui.disneyhero.nameiteminfoadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.disneyheroes.databinding.ItemNameInfoBinding

class NameItemInfoViewHolder(
    private val binding: ItemNameInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.textNameItemInfo.text = item
    }
}