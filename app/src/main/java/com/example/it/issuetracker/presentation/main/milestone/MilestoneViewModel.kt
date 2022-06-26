package com.example.it.issuetracker.presentation.main.milestone

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.toMilestone
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import com.example.it.issuetracker.presentation.main.issue.list.Mode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MilestoneViewModel(
    private val milestoneRepository: MilestoneRepository
) : ViewModel() {

    private var _milestoneList = MutableStateFlow<List<MileStone>>(emptyList())
    val milestoneList = _milestoneList.asStateFlow()

    private var _dataLoading = MutableStateFlow(false)
    val dataLoading = _dataLoading.asStateFlow()

    private var _editMode = MutableStateFlow(false)
    val editMode = _editMode.asStateFlow()

    private var _completeDelete = MutableStateFlow(false)
    val completeDelete = _completeDelete.asStateFlow()

    private var _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    init {
        getMilestoneInfoList()
    }

    fun getMilestoneInfoList() {
        viewModelScope.launch {
            _dataLoading.value = true

            val result = milestoneRepository.getMilestoneInfoList()
            if (result.isSuccess) {
                _milestoneList.value = result.getOrDefault(emptyList()).map { milestoneDto ->
                    milestoneDto.toMilestone()
                }

                Log.d("test", _milestoneList.value.toString())

            } else {
                _error.emit(R.string.network_error)
            }
            _dataLoading.value = false
            _completeDelete.value = false
        }
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
                val result = milestoneRepository.deleteMilestone(it.id)
                if (result.isFailure) {
                    _error.emit(R.string.network_error)
                    return@forEach
                }
            }
            _completeDelete.value = true
        }
    }
}