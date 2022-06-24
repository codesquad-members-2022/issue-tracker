package com.example.it.issuetracker.presentation.main.milestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.dto.toMilestone
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import com.example.it.issuetracker.presentation.main.issue.list.Mode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MilestoneViewModel(
    private val milestoneRepository: MilestoneRepository,
) : ViewModel() {

    private var _milestoneList = MutableStateFlow<List<MileStone>>(emptyList())
    val milestoneList = _milestoneList.asStateFlow()

    private var _dataLoading = MutableStateFlow(false)
    val dataLoading = _dataLoading.asStateFlow()

    private var _editMode = MutableStateFlow(false)
    val editMode = _editMode.asStateFlow()

    private var _completeDelete = MutableStateFlow(false)
    val completeDelete = _completeDelete.asStateFlow()

    private var hasData = false

    fun start() {
        if (!hasData) {
            getMilestoneInfoList()
            hasData = true
        }
    }

    fun getMilestoneInfoList() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataLoading.value = true
            milestoneRepository.getMilestoneInfoList().collectLatest { milestoneDtoList ->
                _milestoneList.value = milestoneDtoList.map { dto -> dto.toMilestone() }
                _dataLoading.value = false
            }
        }
        _completeDelete.value = false
    }

    fun changeEditMode(editMode: Boolean) {
        if (editMode == _editMode.value) return

        val editModeList = _milestoneList.value.map {
            if (it.mode == Mode.DEFAULT) {
                it.copy(mode = Mode.EDIT)
            } else {
                it.copy(mode = Mode.DEFAULT)
            }
        }

        _editMode.value = editMode
        _milestoneList.value = editModeList
    }

    fun deleteItems() {
        _completeDelete.value = false
        viewModelScope.launch {
            _milestoneList.value.filter {
                it.isChecked
            }.forEach {
                milestoneRepository.deleteMilestone(it.id)
            }
        }
        _completeDelete.value = true
    }
}