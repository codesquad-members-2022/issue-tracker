package com.example.it.issuetracker.presentation.main.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

class IssueViewModel(
    private val issueRepository: IssueTrackerRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<IssueUiState>(IssueUiState.UnInitialization)
    val uiState = _uiState.asStateFlow()

    private val _cache = MutableStateFlow<CacheIssue>(CacheIssue(sortedMapOf(), ""))
    val cache = _cache.asStateFlow()

    init {
        getIssues()
    }

    private fun getIssues() = viewModelScope.launch {
        _uiState.update { IssueUiState.Loading }
        val activeIssues = issueRepository.getIssue().getOrThrow()
        _uiState.update { IssueUiState.GetIssues(activeIssues) }
        _cache.value = CacheIssue(sortedMapOf(), "")
    }

    fun toggleViewType() {
        when (_uiState.value) {
            is IssueUiState.GetIssues -> {
                val editIssueList = (_uiState.value as IssueUiState.GetIssues).issues.map { issue ->
                    when (issue.viewType) {
                        Mode.DEFAULT -> issue.copy(viewType = Mode.EDIT)
                        Mode.EDIT -> issue.copy(viewType = Mode.DEFAULT)
                    }
                }
                _uiState.update { IssueUiState.GetIssues(editIssueList) }
                _cache.value = CacheIssue(sortedMapOf(), "")
            }
            else -> {}
        }
    }

    fun updateDefaultViewType() {
        when (_uiState.value) {
            is IssueUiState.GetIssues -> {
                val defaultIssueList =
                    (_uiState.value as IssueUiState.GetIssues).issues.map { issue ->
                        issue.copy(viewType = Mode.DEFAULT)
                    }
                _uiState.update { IssueUiState.GetIssues(defaultIssueList) }
                _cache.value = CacheIssue(sortedMapOf(), "")

            }
            else -> {}
        }
    }

    fun deleteIssue() = viewModelScope.launch {
        when (_uiState.value) {
            is IssueUiState.GetIssues -> {
                val filterList =
                    (_uiState.value as IssueUiState.GetIssues).issues.filter { issue -> issue.isChecked }

                val newList: SortedMap<Int, Issue> = sortedMapOf()
                (_uiState.value as IssueUiState.GetIssues).issues.mapIndexed { idx, issue ->
                    if (issue.isChecked) {
                        newList[idx] = issue
                    }
                }

                if (isCheckEmpty(filterList)) return@launch
                val activeIssues = issueRepository.deleteIssue(filterList).getOrThrow()
                _uiState.update { IssueUiState.GetIssues(activeIssues) }
                _cache.value = CacheIssue(newList, "선택한 이슈를 삭제했습니다.")
            }
            else -> {}
        }
    }

    fun closeIssue() = viewModelScope.launch {
        when (_uiState.value) {
            is IssueUiState.GetIssues -> {
                val filterList =
                    (_uiState.value as IssueUiState.GetIssues).issues.filter { issue -> issue.isChecked }

                val newList: SortedMap<Int, Issue> = sortedMapOf()
                (_uiState.value as IssueUiState.GetIssues).issues.mapIndexed { idx, issue ->
                    if (issue.isChecked) {
                        newList[idx] = issue
                    }
                }

                if (isCheckEmpty(filterList)) return@launch
                val activeIssue = issueRepository.closeIssue(filterList).getOrThrow()
                _uiState.update { IssueUiState.GetIssues(activeIssue) }
                _cache.value = CacheIssue(newList, "선택한 이슈를 닫았습니다.")
            }
            else -> {}
        }
    }

    private fun isCheckEmpty(filterList: List<Issue>): Boolean {
        if (filterList.isEmpty()) {
            updateDefaultViewType()
            return true
        }
        return false
    }

    fun revertIssue(list: SortedMap<Int, Issue>) = viewModelScope.launch {
        val activeIssues = issueRepository.revertIssue(list).getOrThrow()
        _uiState.update { IssueUiState.GetIssues(activeIssues) }
    }

    fun getFilterList(value: List<Issue>) {
        _uiState.update { IssueUiState.GetIssues(value) }
    }
}