package com.team1.issuetracker.common

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.team1.issuetracker.ui.main.label.AddLabelViewModel

@BindingAdapter("setLabelColor")
fun setLabelColor(editText: EditText, setLabelBackground: (Int, String) -> Unit) {
    var text = ""
    editText.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            text = s.toString()
        }
    })

    editText.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
        if (!focus) {
            runCatching {
                Color.parseColor(text)
            }.onSuccess {
                setLabelBackground(it, text)
                Log.d("TAG", "success")
            }.onFailure {
                Toast.makeText(editText.context, "올바른 색상을 입력해주세요.", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "fail")

            }
        }
    }
}

@BindingAdapter("setLabelColor")
fun setLabelColor(cardView: CardView, color: String) {
    cardView.setCardBackgroundColor(Color.parseColor(color))
}