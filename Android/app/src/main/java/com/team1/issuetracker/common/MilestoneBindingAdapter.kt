package com.team1.issuetracker.common

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.team1.issuetracker.ui.main.milestone.AddMilestoneViewModel
import java.text.SimpleDateFormat

@BindingAdapter("setDate")
fun setDate(editText: EditText, setDate: (String) -> Unit) {
    var text = ""
    editText.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {
            text = s.toString()
        }
    })

    editText.onFocusChangeListener = View.OnFocusChangeListener { _, focus ->
        if (!focus) {
            val format = SimpleDateFormat("yyyy-mm-dd")
            runCatching {
                format.parse(text)
            }.onSuccess {
                setDate(text)
                Log.d("TAG", "success")
            }.onFailure {
                Toast.makeText(editText.context, "올바른 날짜를 입력해주세요.", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "fail")

            }
        }
    }
}

//@BindingAdapter("setOpenIssue")
//fun setOpenIssue(textView: TextView, openIssue: Int) {
//    textView.text = "열린 이슈 ${openIssue}개"
//}
//
//@BindingAdapter("setClosedIssue")
//fun setClosedIssue(textView: TextView, closedIssue: Int) {
//    textView.text = "닫힌 이슈 ${closedIssue}개"
//}



