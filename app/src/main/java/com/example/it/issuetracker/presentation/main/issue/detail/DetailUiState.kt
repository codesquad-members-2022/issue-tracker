package com.example.it.issuetracker.presentation.main.issue.detail

import com.example.it.issuetracker.domain.model.IssueDetail

sealed class DetailUiState {
    object UnInitialization : DetailUiState()
    object Loading : DetailUiState()
    data class GetDetailIssue(val issue: IssueDetail) : DetailUiState()
}