package com.example.issu_tracker.ui.issue

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.issu_tracker.data.Issue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class IssueViewModel : ViewModel() {

    private val _selectedIssueList = MutableStateFlow<MutableList<Issue>>(mutableListOf())
    val selectedIssueList: StateFlow<List<Issue>> = _selectedIssueList


     fun addSelectedIssue(issue: Issue) {
        _selectedIssueList.value.add(issue)

         Log.d("testforClose" , selectedIssueList.value.toString())
    }

     fun deleteSelectedIssue(issue: Issue) {
        _selectedIssueList.value.remove(issue)
         Log.d("testforClose" , selectedIssueList.value.toString())
    }
}