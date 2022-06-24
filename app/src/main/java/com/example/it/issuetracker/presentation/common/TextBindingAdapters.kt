package com.example.it.issuetracker.presentation.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.it.issuetracker.R
import com.example.it.issuetracker.presentation.common.utils.calculateTime
import java.text.SimpleDateFormat

@BindingAdapter("formatDate", "writer")
fun dateAndWriterFormat(view: TextView, stringDate: String?, name: String?) {
    if (!stringDate.isNullOrBlank() && !name.isNullOrBlank()) {
        val format = SimpleDateFormat(view.context.getString(R.string.date_format))
        val date = format.parse(stringDate)
        val result = date?.let { calculateTime(date) } ?: ""
        view.text = view.context.getString(R.string.issue_write_time, result, name)
    }
}

@BindingAdapter("formatDate")
fun dateFormat(view: TextView, stringDate: String?) {
    if (!stringDate.isNullOrBlank()) {
        val format = SimpleDateFormat(view.context.getString(R.string.date_format))
        val date = format.parse(stringDate)
        val result = date?.let { calculateTime(date) } ?: ""
        view.text = result
    }
}


@BindingAdapter("issueNumber")
fun issueFormat(view: TextView, number: Long) {
    view.text = view.context.getString(R.string.issue_number, number)
}

@BindingAdapter("exists")
fun hasManager(view: TextView, name: String?) {
    view.text = name ?: "없음"
}