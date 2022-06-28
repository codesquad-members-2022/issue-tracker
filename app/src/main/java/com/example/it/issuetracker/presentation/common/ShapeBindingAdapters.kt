package com.example.it.issuetracker.presentation.common

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.it.issuetracker.R

@BindingAdapter("drawableBackgroundColor", "drawableTextColor")
fun drawableColor(textView: TextView, color: String, textColor: String) {
    val drawable = ContextCompat.getDrawable(
        textView.context,
        R.drawable.shape_button_round50
    ) as GradientDrawable
    drawable.setColor(Color.parseColor(color))
    textView.background = drawable
    textView.setTextColor(Color.parseColor(textColor))
}