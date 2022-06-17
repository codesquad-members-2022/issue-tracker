package com.example.it.issuetracker.presentation.main.label

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.data.dto.LabelInfo

@BindingAdapter("app:setSubmitListItems")
fun setSubmitListItems(recyclerView: RecyclerView, items: List<LabelInfo>?) {
    if (items == null) return
    (recyclerView.adapter as LabelListAdapter).submitList(items)
}
