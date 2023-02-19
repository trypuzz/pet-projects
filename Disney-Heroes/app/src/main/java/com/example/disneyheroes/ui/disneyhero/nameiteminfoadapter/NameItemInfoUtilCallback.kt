package com.example.disneyheroes.ui.disneyhero.nameiteminfoadapter

import androidx.recyclerview.widget.DiffUtil

class NameItemInfoUtilCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return false
    }
}