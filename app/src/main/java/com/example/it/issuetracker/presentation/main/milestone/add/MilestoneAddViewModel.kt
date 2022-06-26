package com.example.it.issuetracker.presentation.main.milestone.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.model.toMileStoneDto
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MilestoneAddViewModel(
    private val milestoneRepository: MilestoneRepository
) : ViewModel() {

    var title = MutableStateFlow("")

    var description = MutableStateFlow("")

    var deadline = MutableStateFlow("")

    private var _completeSaveMilestone = MutableStateFlow(false)
    val completeSaveLabel = _completeSaveMilestone.asStateFlow()

    private val _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    fun addMilestone(title: String, description: String, deadline: String) {
        _completeSaveMilestone.value = false
        this.title.value = title
        this.description.value = description
        this.deadline.value = deadline

        val milestone = AddMilestoneDto(title, description, deadline)
        viewModelScope.launch {
            val result = milestoneRepository.addMilestone(milestone)
            if (result.isFailure) _error.emit(R.string.network_error)
            else _completeSaveMilestone.value = true
        }
    }

    fun editMilestone(milestone: MileStone) {
        viewModelScope.launch {
            val result = milestoneRepository.editMilestone(milestone.toMileStoneDto())
            if (result.isFailure) _error.emit(R.string.network_error)
            else _completeSaveMilestone.value = true
        }
    }

    fun setData(milestone: MileStone) {
        this.title.value = milestone.title
        if (milestone.description != null) {
            this.description.value = milestone.description
        }
        if (milestone.deadLine != null) {
            this.deadline.value = milestone.deadLine
        }
    }

    fun clearDeadline() {
        this.deadline.value = ""
    }
}
