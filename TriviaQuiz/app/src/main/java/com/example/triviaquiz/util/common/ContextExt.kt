package com.example.triviaquiz.util.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.triviaquiz.R
import java.util.*

fun Context.createLoading(): Dialog {
    val dialogLoading = Dialog(this)
    return dialogLoading.also {
        it.requestWindowFeature(Window.FEATURE_NO_TITLE)
        it.setContentView(R.layout.loading)
        it.setCancelable(false)
        it.setCanceledOnTouchOutside(false)
        Objects.requireNonNull(dialogLoading.window!!).setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Glide.with(this)
            .asGif()
            .load(R.drawable.loading_gif)
            .apply(RequestOptions().override(80, 80))
            .into(it.findViewById(R.id.loadingGif) as ImageView)
    }
}