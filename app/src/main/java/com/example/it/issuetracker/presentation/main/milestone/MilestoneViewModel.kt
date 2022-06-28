package com.example.it.issuetracker.presentation.main.milestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.toMilestone
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import com.example.it.issuetracker.presentation.main.issue.list.Mode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MilestoneViewModel(
    private val milestoneRepository: MilestoneRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(MilestoneUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMilestoneInfoList()
    }

    fun getMilestoneInfoList() {
        viewModelScope.launch {
            _uiState.update { it.copy(dataLoading = true) }
            val result = milestoneRepository.getMilestoneInfoList()
            if (result.isSuccess) {
                val milestoneList = result.getOrDefault(emptyList()).map { milestoneDto ->
                    milestoneDto.toMilestone()
                }
                _uiState.update { it.copy(milestoneList = milestoneList) }
            } else {
                _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            }
            _uiState.update { it.copy(dataLoading = false, completeTask = false) }
        }
    }

    fun changeEditMode(editMode: Boolean) {
        if (editMode == _uiState.value.editMode) return
        val editModeList = _uiState.value.milestoneList.map { milestone ->
            if (milestone.mode == Mode.DEFAULT) milestone.copy(mode = Mode.EDIT)
            else milestone.copy(mode = Mode.DEFAULT)
        }
        _uiState.update { it.copy(editMode = editMode, milestoneList = editModeList) }
    }

    fun deleteItems() {
        viewModelScope.launch {
            _uiState.update { it.copy(completeTask = false) }
            _uiState.value.milestoneList.filter { it.isChecked }.forEach { milestone ->
                val result = milestoneRepository.deleteMilestone(milestone.id)
                if (result.isFailure) {
                    _uiState.update { it.copy(errorMsgId = R.string.network_error) }
                    return@forEach
                }
            }
            _uiState.update { it.copy(completeTask = true) }
        }
    }
}