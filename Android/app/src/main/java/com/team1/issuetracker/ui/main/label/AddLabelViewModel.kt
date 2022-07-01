package com.team1.issuetracker.ui.main.label

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.team1.issuetracker.common.LabelColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddLabelViewModel @Inject constructor() : ViewModel() {

    private val _labelText = MutableStateFlow("")
    val labelText: StateFlow<String> = _labelText

    private var description = ""

    private val _saveButtonState = MutableStateFlow(false)
    val saveButtonState: StateFlow<Boolean> = _saveButtonState

    private val _labelColorText = MutableStateFlow("")
    val labelColorText: StateFlow<String> = _labelColorText

    private val _labelColor = MutableStateFlow(0)
    val labelColor: StateFlow<Int> = _labelColor


    val setLabelBackgroundColor = { color: Int, colorName: String ->
        _labelColor.value = color
        _labelColorText.value = colorName
    }

    fun onTitleTextWatcher(text: CharSequence?, start: Int, before: Int, count: Int) {
        Log.d("TAG", "onTitleTextWatcher")
        _labelText.value = text.toString()
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

    fun onDescriptionTextWatcher(text: Editable?) {
        description = text.toString()
    }

    fun setLabelBackgroundRandomColor() {
        val random = Random()

        val red = random.nextInt(256)
        val green = random.nextInt(256)
        val blue = random.nextInt(256)

        val color = LabelColor()

        _labelColor.value = color.getLabelColor(red, green, blue)
        _labelColorText.value = color.getColorLabel(red, green, blue)
    }



}