package com.example.issu_tracker.ui.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IssueEditorViewModel : ViewModel() {

    private val _issueBodyStateFlow = MutableStateFlow<String>("")
    val issueBodyStateFlow = _issueBodyStateFlow.asStateFlow()

    fun saveIssueBodyText(text: String) {
        _issueBodyStateFlow.value = text
    }

}