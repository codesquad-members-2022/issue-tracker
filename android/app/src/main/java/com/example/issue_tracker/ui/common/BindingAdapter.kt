package com.example.issue_tracker.ui.common

import android.widget.CheckBox
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("checkBoxVisible")
fun checkBoxVisible(view: CheckBox, isLongClicked: Boolean?) {
    view.isVisible = isLongClicked == true
}
