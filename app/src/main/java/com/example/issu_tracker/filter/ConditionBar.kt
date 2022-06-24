package com.example.issu_tracker.filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Spinner
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

    val spinner: Spinner = binding.spinnerCondition

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ConditionBar,
            0,
            0
        ).apply {
            try {
                setConditionTitle(getResourceId(R.styleable.ConditionBar_conditionBarTitle, 0))
            } finally {
                recycle()
            }
        }
    }

    private fun setConditionTitle(textRes: Int) {
        if (textRes != 0) binding.tvConditionTitle.setText(textRes)
    }

}