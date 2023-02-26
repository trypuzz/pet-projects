package com.example.disneyheroes.ui.disneyhero.infoheroadapter

import androidx.recyclerview.widget.RecyclerView
import com.example.disneyheroes.databinding.ItemListInfoBinding
import com.example.disneyheroes.models.InfoHero
import com.example.disneyheroes.ui.disneyhero.nameiteminfoadapter.NameItemInfoAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class InfoHeroViewHolder(
    private val binding: ItemListInfoBinding
) :  RecyclerView.ViewHolder(binding.root) {

    fun bind(item: InfoHero) {
        binding.nameCategory.text = item.category
        binding.listInfo.run {
            if (adapter == null) {
                adapter = NameItemInfoAdapter()
                layoutManager = FlexboxLayoutManager(binding.root.context).apply {
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                    flexWrap = FlexWrap.WRAP
                }
            }
            (adapter as? NameItemInfoAdapter)?.submitList(item.list)
        }
    }
}