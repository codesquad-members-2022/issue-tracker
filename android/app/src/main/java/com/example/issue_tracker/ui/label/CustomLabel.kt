package com.example.issue_tracker.ui.label

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.CustomLabelBinding

class CustomLabel(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var binding: CustomLabelBinding

    init {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_label, this, true)
        getAttrs(attributeSet)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLabel)

//        typedArray.getString(R.styleable.CustomLabel_title)?.let { setLabelText(it) }

        typedArray.recycle()
    }

    private fun setLabelText(text: String) {
        binding.tvCustomLabelTitle.text = text
    }

    fun setLabelTitle(text: String?) {
        binding.tvCustomLabelTitle.text = text
    }

    fun setLabelColor(color: String?) {
        val parsedColor = color.let { Color.parseColor(it) }
        val mode = BlendMode.DST_OVER

        val drawable = context.getDrawable(R.drawable.custom_label_background)
            ?.let { DrawableCompat.wrap(it) }

        drawable?.colorFilter = BlendModeColorFilter(parsedColor, mode)

        binding.customLabelLayout.background = drawable
    }
}
