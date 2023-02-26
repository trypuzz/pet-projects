package com.example.disneyheroes.ui.banners

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.disneyheroes.databinding.ViewBannerAddDeleteFavoriteBinding

class BannerViewAddDeleteFavorite(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var binding: ViewBannerAddDeleteFavoriteBinding

    init {
        binding =
            ViewBannerAddDeleteFavoriteBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setTitle(title: String) {
        binding.textTitle.text = title
    }

    fun setClickClose(onClick: () -> Unit) {
        binding.imageClose.setOnClickListener {
            this.visibility = View.GONE
            onClick()
        }
    }

    fun setImageAddDeleteHero(resId: Int) {
        binding.imageAdded.setImageResource(resId)
    }
}