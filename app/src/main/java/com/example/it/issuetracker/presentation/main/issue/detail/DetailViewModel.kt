package com.example.it.issuetracker.presentation.main.issue.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val issueRepository: IssueTrackerRepository,
) : ViewModel() {

    private val _issueDetail = MutableStateFlow<DetailUiState>(DetailUiState.UnInitialization)
    val issueDetail = _issueDetail.asStateFlow()

    private val _currentPosition = MutableStateFlow<Int>(0)
    val currentPosition = _currentPosition.asStateFlow()

    private val _focusPosition = MutableStateFlow<Int>(0)
    val focusPosition = _focusPosition.asStateFlow()

    fun getIssueDetail(id: Long) = viewModelScope.launch {
        _issueDetail.update { DetailUiState.Loading }
        issueRepository.getIssueDetail(id).collectLatest { issueDetail ->
            _issueDetail.update { DetailUiState.GetDetailIssue(issueDetail) }
        }
    }

    fun addComment(id: Long, text: String) = viewModelScope.launch {
        if (text.isBlank()) return@launch
        issueRepository.addComment(id, text)
        when (val state = _issueDetail.value) {
            is DetailUiState.GetDetailIssue -> {
                _focusPosition.update { state.issue.comments.size }
            }
            else -> Unit
        }
        getIssueDetail(id)
    }

    private fun getFocusPosition(id: Long) {
        when (val state = _issueDetail.value) {
            is DetailUiState.GetDetailIssue -> {
                val comment = state.issue.comments.find { it.id == id } ?: return
                val index = state.issue.comments.indexOf(comment)
                _focusPosition.update { index }
            }
            else -> Unit
        }
    }

    fun addLike(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addLike(id, uid)
        getFocusPosition(uid)
    }

    fun addBest(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addBest(id, uid)
        getFocusPosition(uid)
    }

    fun addHate(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addHate(id, uid)
        getFocusPosition(uid)
    }

    fun addOk(id: Long, uid: Long) = viewModelScope.launch {
        issueRepository.addOk(id, uid)
        getFocusPosition(uid)
    }

    fun upPosition() {
        when (val state = _issueDetail.value) {
            is DetailUiState.GetDetailIssue -> {
                if (_currentPosition.value - 1 < 0) return
                _currentPosition.value = (_currentPosition.value - 1)
            }
            else -> {}
        }
    }

    fun downPosition() {
        when (val state = _issueDetail.value) {
            is DetailUiState.GetDetailIssue -> {
                if (_currentPosition.value + 1 > state.issue.comments.size - 1) return
                _currentPosition.value = (_currentPosition.value + 1)
            }
            else -> {}
        }
    }

}