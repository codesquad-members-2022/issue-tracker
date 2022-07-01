package com.example.it.issuetracker.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}