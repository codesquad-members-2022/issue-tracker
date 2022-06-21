package com.example.it.issuetracker.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.ViewFilterBinding

class CustomFilter(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val binding: ViewFilterBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_filter, this, true)
    val spinner: Spinner = binding.spinnerItem

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FilterCondition)
        binding.tvTitle.text = a.getString(R.styleable.FilterCondition_title).toString()
        a.recycle()
    }
}