package com.irmak.themoviedc.ui.extensions


import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
var isBackOrNext = 5
var observChoice = 0
fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(context.applicationContext)
        .load(imageUrl)
        .into(this)
}
fun loadImageWithDrawable(url: String, imageView: ImageView) {
    Glide.with(imageView)
        .load(url)
        .into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageView.setImageDrawable(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // Not used
            }
        })
}