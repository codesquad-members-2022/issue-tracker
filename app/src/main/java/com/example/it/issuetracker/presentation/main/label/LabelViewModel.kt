package com.example.it.issuetracker.presentation.main.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.presentation.main.issue.Mode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LabelViewModel(
    private val labelRepository: LabelRepository,
) : ViewModel() {

    private var _labelList = MutableStateFlow<List<Label>>(emptyList())
    val labelList = _labelList.asStateFlow()

    private var _dataLoading = MutableStateFlow(false)
    val dataLoading = _dataLoading.asStateFlow()

    private var _editMode = MutableStateFlow(false)
    val editMode = _editMode.asStateFlow()

    private var _completeDelete = MutableStateFlow(false)
    val completeDelete = _completeDelete.asStateFlow()

    private var hasData = false

    fun start() {
        if (!hasData) {
            getLabelInfoList()
            hasData = true
        }
    }

    fun getLabelInfoList() {
        viewModelScope.launch {
            _dataLoading.value = true
            labelRepository.getLabelInfoList().collectLatest { labelDtoList ->
                _labelList.value = labelDtoList.map { dto ->
                    Label(
                        dto.id,
                        dto.title,
                        dto.description,
                        dto.color,
                        dto.textColor
                    )
                }
                _dataLoading.value = false
            }
        }
        _completeDelete.value = false
    }

    fun changeEditMode(editMode: Boolean) {
        if (editMode == _editMode.value) return

        val editModeList = _labelList.value.map {
            if (it.mode == Mode.DEFAULT) {
                it.copy(mode = Mode.EDIT)
            } else {
                it.copy(mode = Mode.DEFAULT)
            }
        }

        _editMode.value = editMode
        _labelList.value = editModeList
    }

    fun deleteItems() {
        _completeDelete.value = false
        viewModelScope.launch {
            _labelList.value.filter {
                it.isChecked
            }.forEach {
                labelRepository.deleteLabelInfo(it.id)
            }
        }
        _completeDelete.value = true
    }
}