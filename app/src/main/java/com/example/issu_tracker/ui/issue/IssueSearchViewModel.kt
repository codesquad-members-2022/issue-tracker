package com.example.issu_tracker.ui.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.Issue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class IssueSearchViewModel @Inject constructor() : ViewModel() {

    private val issueMap = mutableMapOf<String, Issue>()
    private val _searchedIssueStateFlow = MutableStateFlow<List<Issue>>(listOf())
    val searchedIssueStateFlow = _searchedIssueStateFlow.asStateFlow()

    fun initIssueList(issueList: List<Issue>) {
        issueList.forEach {
            issueMap[it.title] = it
        }
    }

    fun filterIssueBySearch(inputText: String) {
        if (inputText.isBlank()) {
            _searchedIssueStateFlow.value = issueMap.values.toList()
        } else {
            _searchedIssueStateFlow.value = issueMap.filterKeys { issueTitle ->
                issueTitle.contains(inputText)
            }.values.toList()
        }
    }
}