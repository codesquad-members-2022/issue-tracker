package com.team1.issuetracker.ui.main.milestone

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat

class AddMilestoneViewModel: ViewModel() {
    private val _title = MutableStateFlow("")
    val title : StateFlow<String> = _title

    private val _content = MutableStateFlow("")
    val content: StateFlow<String> = _content

    private val _date = MutableStateFlow("")
    val date : StateFlow<String> = _date

    private val _saveButtonState = MutableStateFlow(false)
    val saveButtonState: StateFlow<Boolean> = _saveButtonState

    fun setTitle(text: CharSequence?, start: Int, before: Int, count: Int) {
        _title.value = text.toString()
        when {
            text.toString() != "" -> {
                Log.d("TAG", "binding.etTitle.text ${text}, $start, $before, $count")
                _saveButtonState.value = true
            }
            text.toString() == "" -> {
                Log.d("TAG", "blank ${text}, $start, $before, $count")
                _saveButtonState.value = false
            }
        }

    }

    fun setContent(text: Editable?) {
        _content.value = text.toString()
    }

    fun setDate(text: String) {
        _date.value = text


    }

}

