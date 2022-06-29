package com.example.it.issuetracker.presentation.main.issue.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
        issueRepository.getIssue().collectLatest { issues ->
            _uiState.update { IssueUiState.GetIssues(issues) }
        }
        _cache.value = CacheIssue(sortedMapOf(), "")
    }

    fun toggleViewType() {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val editIssueList = state.issues.map { issue ->
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
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val defaultIssueList =
                    state.issues.map { issue ->
                        issue.copy(viewType = Mode.DEFAULT)
                    }
                _uiState.update { IssueUiState.GetIssues(defaultIssueList) }
                _cache.value = CacheIssue(sortedMapOf(), "")

            }
            else -> {}
        }
    }

    fun deleteIssue() = viewModelScope.launch {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val filterList = state.issues.filter { issue -> issue.isChecked }

                val newList: SortedMap<Int, Issue> = sortedMapOf()
                state.issues.mapIndexed { idx, issue ->
                    if (issue.isChecked) {
                        newList[idx] = issue
                    }
                }

                if (isCheckEmpty(filterList)) return@launch
                issueRepository.deleteIssue(filterList)
                getIssues()
                _cache.value = CacheIssue(newList, "선택한 이슈를 삭제했습니다.")
            }
            else -> {}
        }
    }

    fun deleteIssue(id: Long) = viewModelScope.launch {
        issueRepository.deleteIssue(id)
    }

    fun closeIssue() = viewModelScope.launch {
        when (val state = _uiState.value) {
            is IssueUiState.GetIssues -> {
                val filterList = state.issues.filter { issue -> issue.isChecked }

                val newList: SortedMap<Int, Issue> = sortedMapOf()
                state.issues.mapIndexed { idx, issue ->
                    if (issue.isChecked) {
                        newList[idx] = issue
                    }
                }

                if (isCheckEmpty(filterList)) return@launch
                issueRepository.closeIssue(filterList)
                getIssues()
                _cache.value = CacheIssue(newList, "선택한 이슈를 닫았습니다.")
            }
            else -> {}
        }
    }

    fun closeIssue(id: Long) = viewModelScope.launch {
        issueRepository.closeIssue(id)
    }

    private fun isCheckEmpty(filterList: List<Issue>): Boolean {
        if (filterList.isEmpty()) {
            updateDefaultViewType()
            return true
        }
        return false
    }

    fun revertIssue(list: SortedMap<Int, Issue>) = viewModelScope.launch {
        issueRepository.revertIssue(list)
        getIssues()
    }

    fun getFilterList(value: List<Issue>) {
        if (value.isEmpty()) {
            _uiState.update { IssueUiState.NotFound }
            return
        }
        _uiState.update { IssueUiState.GetIssues(value) }
    }

    fun addLike(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addLike(id, uid)
    }

    fun addBest(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addBest(id, uid)
    }

    fun addHate(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addHate(id, uid)
    }

    fun addOk(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addOk(id, uid)
    }
}