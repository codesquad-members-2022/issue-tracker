package com.example.issu_tracker.ui.issue

import androidx.lifecycle.ViewModel
import com.example.issu_tracker.data.IssueList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class IssueSearchViewModel @Inject constructor() : ViewModel() {

    private val issueMap = mutableMapOf<String, IssueList.Issue>()
    private val _searchedIssueStateFlow = MutableStateFlow<List<IssueList.Issue>>(listOf())
    val searchedIssueStateFlow = _searchedIssueStateFlow.asStateFlow()

    fun initIssueList(issueList: List<IssueList>) {
        issueList.forEach {
            if (it is IssueList.Issue)
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