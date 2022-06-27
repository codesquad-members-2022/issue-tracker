package com.example.it.issuetracker.presentation.main.label.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.toLabelDto
import com.example.it.issuetracker.domain.repository.LabelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LabelAddViewModel(
    private val labelRepository: LabelRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewLabelUiState())
    val uiState = _uiState.asStateFlow()
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
        _uiState.update {
            it.copy(
                completeTask = false,
                title = title,
                description = description,
                color = backgroundColor,
                textColor = textColor
            )
        }
        val addLabelDto = AddLabelDto(title, description, backgroundColor, textColor)
        viewModelScope.launch {
            val result = labelRepository.addLabel(addLabelDto)
            if (result.isFailure) _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            else _uiState.update { it.copy(completeTask = true) }
        }
    }

    fun editLabel(label: Label) {
        viewModelScope.launch {
            val result = labelRepository.editLabel(label.toLabelDto())
            if (result.isFailure) _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            else _uiState.update { it.copy(completeTask = true) }
        }
    }

    fun randomColor() {
        val color = colorMap.keys.random()
        val contentColor = colorMap[color]
        _uiState.update { it.copy(color = color, textColor = contentColor ?: "#FFFFFFFF") }
    }

    fun setData(label: Label) {
        _uiState.update {
            it.copy(
                title = label.title,
                description = label.description,
                color = label.color,
                textColor = label.textColor
            )
        }
    }

    fun setTitle(title: String) = _uiState.update { it.copy(title = title) }
    fun setDescription(description: String) = _uiState.update { it.copy(description = description) }
}
