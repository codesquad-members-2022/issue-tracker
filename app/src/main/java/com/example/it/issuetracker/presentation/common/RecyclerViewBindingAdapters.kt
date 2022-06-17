package com.example.it.issuetracker.presentation.common

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.presentation.main.label.LabelListAdapter

@BindingAdapter("app:setSubmitListItems")
fun setSubmitListItems(recyclerView: RecyclerView, items: List<LabelDto>?) {
    if (items == null) return
    (recyclerView.adapter as LabelListAdapter).submitList(items)
}
