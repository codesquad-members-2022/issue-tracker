package com.example.it.issuetracker.presentation.main.milestone.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.model.toMileStoneDto
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MilestoneAddViewModel(
    private val milestoneRepository: MilestoneRepository
) : ViewModel() {

    var title = MutableStateFlow("")

    var description = MutableStateFlow("")

    var deadline = MutableStateFlow("")

    private var _completeSaveLabel = MutableStateFlow(false)
    val completeSaveLabel = _completeSaveLabel.asStateFlow()

    fun addMilestone(title: String, description: String, deadline: String) {
        _completeSaveLabel.value = false
        this.title.value = title

        this.description.value = description

        this.deadline.value = deadline

        viewModelScope.launch {
            milestoneRepository.addMilestone(title, description, deadline)
            _completeSaveLabel.value = true
        }
    }

    fun editMilestone(milestone: MileStone) {
        viewModelScope.launch {
            milestoneRepository.editMilestone(milestone.toMileStoneDto())
            _completeSaveLabel.value = true
        }
    }

    fun setData(milestone: MileStone) {
        this.title.value = milestone.title
        this.description.value = milestone.description!!
        this.deadline.value = milestone.deadLine!!
    }

    fun clearDeadline() {
        this.deadline.value = ""
    }
}
