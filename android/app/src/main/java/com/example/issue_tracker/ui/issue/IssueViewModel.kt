package com.example.issue_tracker.ui.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.model.Issue
import com.example.issue_tracker.repository.IssueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(private val issueRepository: IssueRepository) : ViewModel() {

    private val _issue = MutableStateFlow<MutableList<Issue>>(mutableListOf())
    val issue: StateFlow<MutableList<Issue>> = _issue

    // 이슈 리스트를 가져오는 함수
    // API 로 가져와 처리하는 로직으로 변경 예정
    fun getIssue() {
        viewModelScope.launch {
            issueRepository.getIssue().collect { issue ->
                _issue.value = issue
            }
        }
    }
}