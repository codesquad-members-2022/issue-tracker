package com.example.issu_tracker.ui.issue

import androidx.lifecycle.ViewModel
import com.example.issu_tracker.data.IssueList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IssueViewModel : ViewModel() {

    private val _selectedIssueList = MutableStateFlow<List<IssueList>>(mutableListOf())
    val selectedIssueList: StateFlow<List<IssueList>> = _selectedIssueList

    fun addSelectedIssue(issue: IssueList) {
        val newList = _selectedIssueList.value.toMutableList()
        newList.add(issue)
        _selectedIssueList.value = newList
    }

    fun deleteSelectedIssue(issue: IssueList) {
        val newList = _selectedIssueList.value.toMutableList()
        newList.remove(issue)
        _selectedIssueList.value = newList
        println("지워짐?")
    }

    fun clearSelectedList() {
        val newList = _selectedIssueList.value.toMutableList()
        newList.clear()
        _selectedIssueList.value = newList
    }
}