package com.example.issu_tracker.ui.common

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.CustomImageButtonBinding

class CustomImageButton : ConstraintLayout {
    lateinit var binding: CustomImageButtonBinding

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
        getAttrs(attrs)
    }

    private fun init(context: Context?) {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_image_button,
            this,
            false
        )
        addView(binding.root)
    }


    private fun getAttrs(attrs: AttributeSet?) {
        // attrs 를 참조함
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageButton)
        setTypeArray(typedArray)
    }

//    private fun setImage(imageUrl: String) {
//        Glide.with(context).load(imageUrl).into(binding.ivCustomButton)
//    }
//
//    private fun setTitle(action: String) {
//        binding.tvCustomButton.text = action
//    }

    private fun setTypeArray(typedArray: TypedArray) {

        val imageResource = typedArray.getResourceId(
            R.styleable.CustomImageButton_customImageButtonImage, R.drawable.ic_outline_cancel_24
        )
        binding.ivCustomButton.setBackgroundResource(imageResource)

        val title = typedArray.getString(
            R.styleable.CustomImageButton_customImageButtonTitle
        )
        binding.tvCustomButton.text = title

        val color = typedArray.getColor(
            R.styleable.CustomImageButton_customImageButtonTintColor, Color.BLACK
        )
        binding.tvCustomButton.setTextColor(color)
        binding.ivCustomButton.backgroundTintList = ColorStateList.valueOf(color)

        typedArray.recycle()
    }
}
