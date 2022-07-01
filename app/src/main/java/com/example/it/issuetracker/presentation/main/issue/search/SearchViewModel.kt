package com.example.it.issuetracker.presentation.main.issue.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import com.example.it.issuetracker.presentation.main.issue.list.IssueUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: IssueTrackerRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<IssueUiState>(IssueUiState.UnInitialization)
    val uiState = _uiState.asStateFlow()

    fun findIssue(title: String) = viewModelScope.launch {
        _uiState.update { IssueUiState.Loading }
        repository.findIssue(title).collectLatest { issues ->
            if (issues.isEmpty() || title == "") {
                _uiState.update { IssueUiState.GetIssues(emptyList()) }
            } else {
                _uiState.update { IssueUiState.GetIssues(issues) }
            }
        }
    }
}