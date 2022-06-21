package com.example.it.issuetracker.presentation.customview

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.CustomSnackbarBinding
import com.google.android.material.snackbar.Snackbar

class CustomSnackBar(
    view: View,
    private val message: String,
    private val onClick: () -> Unit,
) {

    private val context = view.context
    private val snackbar = Snackbar.make(view, "", 2000)
    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    private val binding: CustomSnackbarBinding =
        DataBindingUtil.inflate(inflater, R.layout.custom_snackbar, null, false)

    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackbarLayout) {
            removeAllViews()
            setPadding(0, 0, 0, 74)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }

    private fun initData() = with(binding) {
        tvMessage.text = message
        btnCancel.setOnClickListener { onClick() }
    }

    fun show() {
        snackbar.show()
    }

    companion object {
        fun make(view: View, message: String, onClick: () -> Unit) =
            CustomSnackBar(view, message, onClick)
    }
}