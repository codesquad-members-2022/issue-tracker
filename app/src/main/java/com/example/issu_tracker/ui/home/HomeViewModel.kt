package com.example.issu_tracker.ui.home

import androidx.lifecycle.ViewModel
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: HomeRepository) : ViewModel() {

    private val _issueList = MutableStateFlow<List<Issue>>(listOf())
    val issueList: StateFlow<List<Issue>> = _issueList

    init {
        val issueDummyData = repository.loadIssues()
        _issueList.value = issueDummyData
    }


}