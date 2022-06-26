package com.example.it.issuetracker.presentation.main.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.presentation.main.issue.list.Mode
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    init {
        getLabelInfoList()
    }

    fun getLabelInfoList() {
        viewModelScope.launch {
            _dataLoading.value = true

            val result = labelRepository.getLabelInfoList()
            if (result.isSuccess) {
                _labelList.value = result.getOrDefault(emptyList()).map { labelDto ->
                    Label(
                        labelDto.id,
                        labelDto.title,
                        labelDto.description,
                        labelDto.color,
                        labelDto.textColor
                    )
                }
            } else {
                _error.emit(R.string.network_error)
            }
            _dataLoading.value = false
            _completeDelete.value = false
        }
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
                val result = labelRepository.deleteLabelInfo(it.id)
                if (result.isFailure) {
                    _error.emit(R.string.network_error)
                    return@launch
                }
            }
        }
        _completeDelete.value = true
    }
}