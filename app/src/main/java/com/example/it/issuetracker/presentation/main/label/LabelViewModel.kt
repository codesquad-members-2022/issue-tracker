package com.example.it.issuetracker.presentation.main.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.toLabel
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.presentation.main.issue.list.Mode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LabelViewModel(
    private val labelRepository: LabelRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(LabelUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getLabelInfoList()
    }

    fun getLabelInfoList() {
        viewModelScope.launch {
            _uiState.update { it.copy(dataLoading = true) }
            val result = labelRepository.getLabelInfoList()
            if (result.isSuccess) {
                val labelList = result.getOrDefault(emptyList()).map { it.toLabel() }
                _uiState.update { it.copy(labelList = labelList) }
            } else {
                _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            }
            _uiState.update { it.copy(dataLoading = false, completeTask = false) }
        }
    }

    fun changeEditMode(editMode: Boolean) {
        if (editMode == _uiState.value.editMode) return
        val editModeList = _uiState.value.labelList.map { label ->
            if (label.mode == Mode.DEFAULT) label.copy(mode = Mode.EDIT)
            else label.copy(mode = Mode.DEFAULT)
        }
        _uiState.update { it.copy(editMode = editMode, labelList = editModeList) }
    }

    fun deleteItems() {
        viewModelScope.launch {
            _uiState.update { it.copy(completeTask = false) }
            val labelList = _uiState.value.labelList
            labelList.filter { it.isChecked }.forEach { label ->
                val result = labelRepository.deleteLabelInfo(label.id)
                if (result.isFailure) {
                    _uiState.update { it.copy(errorMsgId = R.string.network_error) }
                    return@launch
                }
            }
            _uiState.update { it.copy(completeTask = true) }
        }
    }
}