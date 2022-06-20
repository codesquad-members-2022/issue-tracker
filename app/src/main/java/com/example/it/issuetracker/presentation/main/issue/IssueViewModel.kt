package com.example.it.issuetracker.presentation.main.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IssueViewModel(
    private val issueRepository: IssueTrackerRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<IssueUiState>(IssueUiState.UnInitialization)
    val uiState = _uiState.asStateFlow()

    init {
        getIssues()
    }

    private fun getIssues() = viewModelScope.launch {
        _uiState.value = IssueUiState.Loading
        val activeIssues = issueRepository.getIssue().getOrThrow()
        _uiState.value = IssueUiState.GetIssues(activeIssues)
    }

    fun toggleViewType() {
        when (_uiState.value) {
            is IssueUiState.GetIssues -> {
                val editIssueList = (_uiState.value as IssueUiState.GetIssues).issues.map { issue ->
                    when (issue.viewType) {
                        Mode.DEFAULT -> issue.copy(viewType = Mode.EDIT)
                        Mode.EDIT -> issue.copy(viewType = Mode.DEFAULT)
                    }
                }
                _uiState.value = IssueUiState.GetIssues(editIssueList)
            }
            else -> {}
        }
    }

    fun updateDefaultViewType() {
        when (_uiState.value) {
            is IssueUiState.GetIssues -> {
                val defaultIssueList =
                    (_uiState.value as IssueUiState.GetIssues).issues.map { issue ->
                        issue.copy(viewType = Mode.DEFAULT)
                    }
                _uiState.value = IssueUiState.GetIssues(defaultIssueList)
            }
            else -> {}
        }
    }

    fun deleteIssue() = viewModelScope.launch {
        val filterList =
            (_uiState.value as IssueUiState.GetIssues).issues.filter { issue -> issue.isChecked }
        if (isCheckEmpty(filterList)) return@launch
        val activeIssues = issueRepository.deleteIssue(filterList).getOrThrow()
        _uiState.value = IssueUiState.GetIssues(activeIssues)
    }

    fun closeIssue() = viewModelScope.launch {
        val filterList =
            (_uiState.value as IssueUiState.GetIssues).issues.filter { issue -> issue.isChecked }
        if (isCheckEmpty(filterList)) return@launch
        val activeIssue = issueRepository.closeIssue(filterList).getOrThrow()
        _uiState.value = IssueUiState.GetIssues(activeIssue)
    }

    private fun isCheckEmpty(filterList: List<Issue>): Boolean {
        if (filterList.isEmpty()) {
            updateDefaultViewType()
            return true
        }
        return false
    }
}