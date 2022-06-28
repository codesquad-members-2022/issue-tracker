package com.example.it.issuetracker.presentation.main.label.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.toLabelDto
import com.example.it.issuetracker.domain.repository.LabelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LabelAddViewModel(
    private val labelRepository: LabelRepository,
) : ViewModel() {

    var title = MutableStateFlow("")

    var description = MutableStateFlow("")

    var backgroundColor = MutableStateFlow("#B71C1C")

    var textColor = MutableStateFlow("#FFFFFFFF")

    private var _completeSaveLabel = MutableStateFlow(false)
    val completeSaveLabel = _completeSaveLabel.asStateFlow()

    private val colorMap = mapOf(
        "#B71C1C" to "#FFFFFFFF",
        "#E65100" to "#FFFFFFFF",
        "#F57F17" to "#FFFFFFFF",
        "#1B5E20" to "#FFFFFFFF",
        "#0D47A1" to "#FFFFFFFF",
        "#01579B" to "#FFFFFFFF",
        "#4A148C" to "#FFFFFFFF",

        "#F44336" to "#FF000000",
        "#FF9800" to "#FF000000",
        "#FFEE58" to "#FF000000",
        "#4CAF50" to "#FF000000",
        "#42A5F5" to "#FF000000",
        "#0288D1" to "#FF000000",
        "#BA68C8" to "#FF000000"
    )

    fun addLabel(title: String, description: String, backgroundColor: String, textColor: String) {
        _completeSaveLabel.value = false
        this.title.value = title
        this.description.value = description
        this.backgroundColor.value = backgroundColor
        this.textColor.value = textColor

        viewModelScope.launch {
            labelRepository.addLabel(title, description, backgroundColor, textColor)
            _completeSaveLabel.value = true
        }
    }

    fun editLabel(label: Label) {
        viewModelScope.launch {
            labelRepository.editLabel(label.toLabelDto())
            _completeSaveLabel.value = true
        }
    }

    fun randomColor() {
        val color = colorMap.keys.random()
        val contentColor = colorMap[color]
        backgroundColor.value = color
        textColor.value = contentColor ?: "#FFFFFFFF"
    }

    fun setData(label: Label) {
        this.title.value = label.title
        this.description.value = label.description
        this.backgroundColor.value = label.color
        this.textColor.value = label.textColor
    }
}
