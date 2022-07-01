package com.example.it.issuetracker.presentation.main.issue.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class IssueViewModel(
    private val issueRepository: IssueTrackerRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<IssueUiState>(IssueUiState.UnInitialization)
    val uiState = _uiState.asStateFlow()

    private var cache: MutableList<Long> = mutableListOf()

    private val _onCloseEvent = MutableSharedFlow<String>()
    val onCloseEvent = _onCloseEvent.asSharedFlow()

    private val _onDeleteEvent = MutableSharedFlow<String>()
    val onDeleteEvent = _onDeleteEvent.asSharedFlow()

    fun getIssues() = viewModelScope.launch {
        _uiState.update { IssueUiState.Loading }
        issueRepository.getIssue().collectLatest { issues ->
            _uiState.update { IssueUiState.GetIssues(issues) }
        }
    }

    fun toggleViewType() {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val editIssueList = state.issues.map { issue ->
                    when (issue.viewType) {
                        Mode.DEFAULT -> issue.copy(viewType = Mode.EDIT)
                        Mode.EDIT -> issue.copy(viewType = Mode.DEFAULT, isSwiped = false)
                    }
                }
                _uiState.update { IssueUiState.GetIssues(editIssueList) }
                cache.clear()
            }
            else -> {}
        }
    }

    fun updateDefaultViewType() {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val defaultIssueList =
                    state.issues.map { issue ->
                        issue.copy(viewType = Mode.DEFAULT)
                    }
                _uiState.update { IssueUiState.GetIssues(defaultIssueList) }
                cache.clear()
            }
            else -> {}
        }
    }

    fun deleteIssue() = viewModelScope.launch {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val filterList = state.issues.filter { issue -> issue.isChecked }
                val filterIdList = filterList.map { it.id }

                if (isCheckEmpty(filterIdList)) return@launch
                issueRepository.updateIssueClose(false, filterIdList)
                getIssues()
                cache = filterIdList.toMutableList()
                _onDeleteEvent.emit("선택한 이슈를 삭제했습니다.")

            }
            else -> {}
        }
    }

    fun deleteIssue(id: Long) = viewModelScope.launch {
        issueRepository.updateIssueClose(false, listOf(id))
        getIssues()
    }

    fun closeIssue() = viewModelScope.launch {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val filterList = state.issues.filter { issue -> issue.isChecked }
                val filterIdList = filterList.map { it.id }

                if (isCheckEmpty(filterIdList)) return@launch
                issueRepository.updateIssueClose(false, filterIdList)
                getIssues()
                cache = filterIdList.toMutableList()
                _onCloseEvent.emit("선택한 이슈를 닫았습니다.")
            }
            else -> {}
        }
    }

    fun closeIssue(id: Long) = viewModelScope.launch {
        issueRepository.updateIssueClose(false, listOf(id))
        getIssues()
    }

    private fun isCheckEmpty(filterList: List<Long>): Boolean {
        if (filterList.isEmpty()) {
            updateDefaultViewType()
            return true
        }
        return false
    }

    fun revertCloseIssue() = viewModelScope.launch {
        issueRepository.updateIssueClose(true, cache)
        getIssues()
    }

    fun revertDeleteIssue() = viewModelScope.launch {
        issueRepository.updateIssueClose(true, cache)
        getIssues()
    }

    fun getFilterList(value: List<Issue>) {
        if (value.isEmpty()) {
            _uiState.update { IssueUiState.NotFound }
            return
        }
        _uiState.update { IssueUiState.GetIssues(value) }
    }
}