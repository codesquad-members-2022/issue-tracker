package com.example.it.issuetracker.presentation.main.issue.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.datasource.UserSharedPrefDataSource
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val issueRepository: IssueTrackerRepository,
    private val sharedPref: UserSharedPrefDataSource,
) : ViewModel() {

    private val _issueDetail = MutableStateFlow<DetailUiState>(DetailUiState.UnInitialization)
    val issueDetail = _issueDetail.asStateFlow()

    fun getIssueDetail(id: Long) = viewModelScope.launch {
        _issueDetail.update { DetailUiState.Loading }
        issueRepository.getIssueDetail(id).collectLatest { issueDetail ->
            _issueDetail.update { DetailUiState.GetDetailIssue(issueDetail) }
        }
    }

}