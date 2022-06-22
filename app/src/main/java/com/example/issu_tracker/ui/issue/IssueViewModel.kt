package com.example.issu_tracker.ui.issue

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.issu_tracker.data.Issue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IssueViewModel : ViewModel() {

    private val _selectedIssueList = MutableStateFlow<List<Issue>>(mutableListOf())
    val selectedIssueList: StateFlow<List<Issue>> = _selectedIssueList

    fun addSelectedIssue(issue: Issue) {
        val newList = _selectedIssueList.value.toMutableList()
        newList.add(issue)
        _selectedIssueList.value = newList
    }

    fun deleteSelectedIssue(issue: Issue) {
        val newList = _selectedIssueList.value.toMutableList()
        newList.remove(issue)
        _selectedIssueList.value = newList
    }

    fun clearSelectedList() {
        val newList = _selectedIssueList.value.toMutableList()
        newList.clear()
        _selectedIssueList.value = newList
    }
}