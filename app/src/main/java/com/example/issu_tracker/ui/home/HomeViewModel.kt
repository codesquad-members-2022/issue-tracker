package com.example.issu_tracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _issueList = MutableStateFlow<List<Issue>>(listOf())
    val issueList: StateFlow<List<Issue>> = _issueList

    init {
        viewModelScope.launch {
            getIssueList()
        }
    }

    suspend fun getIssueList() {
        val issueDummyData = repository.loadIssues()
        _issueList.value = issueDummyData
    }

    suspend fun deleteIssue(itemId: String) {
        repository.deleteIssue(itemId)
        getIssueList()
    }
}