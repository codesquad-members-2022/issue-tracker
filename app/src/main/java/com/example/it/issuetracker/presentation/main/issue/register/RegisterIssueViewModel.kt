package com.example.it.issuetracker.presentation.main.issue.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.dto.toLabel
import com.example.it.issuetracker.data.dto.toMilestone
import com.example.it.issuetracker.domain.model.IssueDetail
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterIssueViewModel(
    private val labelRepository: LabelRepository,
    private val milestoneRepository: MilestoneRepository,
    private val issueTrackerRepository: IssueTrackerRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterIssueUiState())
    val uiState = _uiState.asStateFlow()

    private val _newIssue = MutableStateFlow<NewIssue>(NewIssue("", ""))
    val newIssue = _newIssue.asStateFlow()

    init {
        _uiState.update { it.copy(dataLoading = false) }
        getLabelList()
        getMilestoneList()
        getAssigneeList()
        _uiState.update { it.copy(dataLoading = true) }
    }

    private fun getLabelList() = viewModelScope.launch {
        labelRepository.getLabelInfoList()
            .onSuccess { labelDtoList ->
                _uiState.update { state ->
                    val labelList = labelDtoList.map { it.toLabel() }
                    val filterItems = state.filterItems.copy(labelList = labelList)
                    state.copy(filterItems = filterItems)
                }
            }
            .onFailure {
                _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            }
    }

    private fun getMilestoneList() = viewModelScope.launch {
        milestoneRepository.getMilestoneInfoList()
            .onSuccess { milestoneDtoList ->
                _uiState.update { state ->
                    val milestoneList = milestoneDtoList.map { it.toMilestone() }
                    val filterItems = state.filterItems.copy(milestoneList = milestoneList)
                    state.copy(filterItems = filterItems)
                }
            }
            .onFailure {
                _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            }
    }

    private fun getAssigneeList() = viewModelScope.launch {
        issueTrackerRepository.getWriter()
            .onSuccess { memberList ->
                _uiState.update { state ->
                    val filterItems = state.filterItems.copy(assigneeList = memberList)
                    state.copy(filterItems = filterItems)
                }
            }
            .onFailure {
                _uiState.update { it.copy(errorMsgId = R.string.network_error) }
            }
    }

    fun saveIssue() = viewModelScope.launch {
        val state = _uiState.value

        // TODO: 서버에 이슈/수정 기능이 완료되면 인덱스 번호로 변경해야 함.
        val labelList = mutableListOf<Label>()
        state.selectedFilters.labelIndex?.forEach { index ->
            labelList.add(state.filterItems.labelList.filter { it.id == index }[0])
        }
        var milestone: MileStone? = null
        val milestoneIndex = state.selectedFilters.milestoneIndex
        if (milestoneIndex != null && milestoneIndex != 0) milestone =
            state.filterItems.milestoneList[milestoneIndex.toInt() - 1]
        val newIssue = NewIssue(
            state.title,
            state.description,
            labelList,
            milestone,
            state.filterItems.assigneeList[0].id
        )

        issueTrackerRepository.saveIssue(newIssue)
        Log.d("test", "saveIssue: $newIssue")
        _newIssue.value = newIssue
    }

    fun setSubject(subject: String) {
        _uiState.update { it.copy(title = subject) }
    }

    fun setDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun setSubjectEmptyState(state: Boolean) {
        _uiState.update {
            val requiredField = it.requiredField.copy(isEmptySubject = state)
            it.copy(requiredField = requiredField)
        }
    }

    fun setDescriptionEmptyState(state: Boolean) {
        _uiState.update {
            val requiredField = it.requiredField.copy(isEmptyDescription = state)
            it.copy(requiredField = requiredField)
        }
    }

    fun setSelectedMilestoneIndex(index: Int) {
        _uiState.update {
            val selectedFilters = it.selectedFilters.copy(milestoneIndex = index)
            it.copy(selectedFilters = selectedFilters)
        }
    }

    fun setSelectedAssigneeIndex(index: Int) {
        _uiState.update {
            val selectedFilters = it.selectedFilters.copy(assigneeIndex = index)
            it.copy(selectedFilters = selectedFilters)
        }
    }

    fun setCheckedLabel(id: Int, isChecked: Boolean) {
        val labelList = _uiState.value.selectedFilters.labelIndex?.toMutableSet()
        if (isChecked) {
            labelList?.add(id)
        } else {
            val removeId = labelList?.find { it == id } ?: return
            labelList.remove(removeId)
        }
        _uiState.update {
            val selectedFilters = it.selectedFilters.copy(labelIndex = labelList?.toList())
            it.copy(selectedFilters = selectedFilters)
        }
    }

    fun setDataForEditIssue(issueDetail: IssueDetail) {
        setSubject(issueDetail.title)
        setDescription(issueDetail.description)
        issueDetail.labels.forEach { label ->
            setCheckedLabel(label.id, true)
        }
        issueDetail.milestones.id?.let {
            setSelectedMilestoneIndex(it.toInt())
        }
        if (issueDetail.manager.isNotEmpty())
            setSelectedAssigneeIndex(issueDetail.manager[0].id.toInt())
    }
}