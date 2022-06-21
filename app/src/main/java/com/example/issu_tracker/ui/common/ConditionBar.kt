package com.example.issu_tracker.ui.common

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.ViewFilterConditionBinding

class ConditionBar(
    context: Context,
    attrs: AttributeSet?,
) :
    ConstraintLayout(context, attrs) {

    private val binding: ViewFilterConditionBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.view_filter_condition, this, true
    )
    val title = binding.tvConditionTitle
    val spinner = binding.spinnerCondition
    val constraintLayout = binding.clFilterContainer

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ConditionBar,
            0,
            0
        ).apply {
            try {
                setConditionTitle(getResourceId(R.styleable.ConditionBar_conditionBarTitle, 0))
                setConditionBackground(getResourceId(R.styleable.ConditionBar_conditionBarBackground, 0))
                setConditionTextAppearance(getResourceId(R.styleable.ConditionBar_conditionBarTextAppearance, 0))
                setConditionSpinnerBackgroundTint(getResourceId(R.styleable.ConditionBar_conditionBarBackgroundTint, 0))
            } finally {
                recycle()
            }
        }
    }

    private fun setConditionTitle(textRes: Int) {
        if (textRes != 0) title.setText(textRes)
    }

    private fun setConditionBackground(colorRes: Int) {
        if (colorRes != 0) {
            constraintLayout.setBackgroundResource(colorRes)
        }
    }

    private fun setConditionTextAppearance(styleRes: Int) {
        if (styleRes != 0) title.setTextAppearance(styleRes)
    }

    private fun setConditionSpinnerBackgroundTint(colorRes: Int) {
        if (colorRes != 0) spinner.backgroundTintList = ColorStateList.valueOf(colorRes)
    }

}