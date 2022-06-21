package com.example.issu_tracker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.FilterCondition
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

    private val _filteredIssueList = MutableStateFlow<List<Issue>>(listOf())
    val filteredIssueList: StateFlow<List<Issue>> = _filteredIssueList

    init {
        viewModelScope.launch {
            getIssueList()
        }
    }

    suspend fun getIssueList() {
        val issueDummyData = repository.loadIssues()
        _issueList.value = issueDummyData
    }

    suspend fun updateIssueSate(itemId: String, boolean: Boolean) {
        repository.updateIssueState(itemId , boolean)
        getIssueList()
    }

    suspend fun deleteIssueList(issueList: List<Issue>) {

    }

    suspend fun updateIssueList(issueList: List<Issue>, boolean: Boolean) {

    }


    fun filterIssueList(condition: FilterCondition) {
        val filteredIssueList = _issueList.value
            .filter {
                if (condition.state.isNotEmpty()) {
                    it.state
                } else {
                    true
                }
            }.filter {
                if (condition.writer.isNotEmpty()) {
                    it.user.name == condition.writer
                } else {
                    true
                }
            }.filter {
                it.label.any { label ->
                    if (condition.label.isNotEmpty()) {
                        label.content == condition.label
                    } else {
                        true
                    }
                }
            }

        _filteredIssueList.value = filteredIssueList
    }
}




