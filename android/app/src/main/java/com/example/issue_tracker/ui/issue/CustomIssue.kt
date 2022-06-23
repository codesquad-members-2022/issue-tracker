package com.example.issue_tracker.ui.issue

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.CustomIssueFilterBinding

class CustomIssue(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet) {

    private val binding: CustomIssueFilterBinding

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.custom_issue_filter, this, true)
        getAttrs(attributeSet)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIssue)
        typedArray.getString(R.styleable.CustomIssue_issueTitle)?.let { setFilterTitle(it) }
        typedArray.getString(R.styleable.CustomIssue_issueChoose)?.let { setFilterChoose(it) }
        typedArray.recycle()
    }

    private fun setFilterTitle(title: String) {
        binding.tvFilterTitle.text = title
    }

    private fun setFilterChoose(choose: String) {
        binding.tvFilterChoose.text = choose
    }
}