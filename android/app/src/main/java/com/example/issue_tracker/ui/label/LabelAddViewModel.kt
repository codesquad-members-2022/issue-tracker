package com.example.issue_tracker.ui.label

import androidx.lifecycle.ViewModel
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.repository.LabelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LabelAddViewModel @Inject constructor(
    private val labelRepository: LabelRepository
) : ViewModel() {

    private val _label: MutableStateFlow<Label> = MutableStateFlow(defaultLabel)
    val label: StateFlow<Label> = _label.asStateFlow()

    val labelTitle: MutableStateFlow<String> = MutableStateFlow("")

    val labelDescription: MutableStateFlow<String> = MutableStateFlow("")

    fun randomColor() {
        val randomA = 255
        val randomR = (0..255).random()
        val randomG = (0..255).random()
        val randomB = (0..255).random()
        val colorA = convertIntToHex(randomA)
        val colorR = convertIntToHex(randomR)
        val colorG = convertIntToHex(randomG)
        val colorB = convertIntToHex(randomB)
        val randomColorString = buildString {
            append("#")
            append(colorA)
            append(colorR)
            append(colorG)
            append(colorB)
        }

        _label.value = Label(null, labelTitle.value, labelDescription.value, randomColorString)
    }

    fun saveLabel() {
        if(_label.value === defaultLabel) {
            labelRepository.addLabelList(Label(null, labelTitle.value, labelDescription.value, INITIAL_COLOR))
            return
        }
        labelRepository.addLabelList(_label.value)
    }

    private fun convertIntToHex(number: Int): String {
        return String.format("%02x", number)
    }

    companion object {
        const val INITIAL_COLOR = "#FF828282"
        val defaultLabel = Label(null, "", null, INITIAL_COLOR)
    }
}