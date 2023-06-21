package com.irmak.themoviedc.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context.applicationContext)
        .load(imageUrl)
        .into(this)
}