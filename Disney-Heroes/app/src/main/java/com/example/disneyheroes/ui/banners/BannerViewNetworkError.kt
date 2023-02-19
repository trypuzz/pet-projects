package com.example.disneyheroes.ui.banners

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.disneyheroes.databinding.ViewBannerNetworkErrorBinding

class BannerViewNetworkError(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var binding: ViewBannerNetworkErrorBinding

    init {
        binding = ViewBannerNetworkErrorBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setClickCancel(onClick: () -> Unit) {
        binding.textCancel.setOnClickListener {
            onClick()
        }
    }

    fun setClickRepeat(onClick: () -> Unit) {
        binding.textRepeat.setOnClickListener {
            onClick()
        }
    }
}