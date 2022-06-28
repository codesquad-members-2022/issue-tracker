package com.example.it.issuetracker.presentation.main.issue.list

import com.example.it.issuetracker.domain.model.Issue

sealed class IssueUiState {
    object UnInitialization : IssueUiState()
    object Loading : IssueUiState()
    object NotFound : IssueUiState()
    data class GetIssues(val issues: List<Issue>) : IssueUiState()
}