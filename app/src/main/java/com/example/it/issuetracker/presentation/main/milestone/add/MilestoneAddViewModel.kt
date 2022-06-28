package com.example.it.issuetracker.presentation.main.milestone.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.model.toMileStoneDto
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MilestoneAddViewModel(
    private val milestoneRepository: MilestoneRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(NewMilestoneUiState())
    val uiState = _uiState.asStateFlow()

    fun addMilestone(title: String, description: String, deadline: String) {
        _uiState.update {
            it.copy(
                completeTask = false,
                title = title,
                description = description,
                deadline = deadline
            )
        }
        val milestone = AddMilestoneDto(title, description, deadline)
        viewModelScope.launch {
            val result = milestoneRepository.addMilestone(milestone)
            if (result.isFailure) _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            else _uiState.update { it.copy(completeTask = true) }
        }
    }

    fun editMilestone(milestone: MileStone) {
        viewModelScope.launch {
            val result = milestoneRepository.editMilestone(milestone.toMileStoneDto())
            if (result.isFailure) _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            else _uiState.update { it.copy(completeTask = true) }
        }
    }

    fun setData(milestone: MileStone) {
        _uiState.update {
            it.copy(
                title = milestone.title,
                description = milestone.description ?: "",
                deadline = milestone.deadLine ?: ""
            )
        }
    }

    fun clearDeadline() {
        _uiState.update { it.copy(deadline = "") }
    }

    fun setTitle(title: String) = _uiState.update { it.copy(title = title) }
    fun setDescription(description: String) = _uiState.update { it.copy(description = description) }
    fun setDeadline(deadline: String) = _uiState.update { it.copy(deadline = deadline) }
}
