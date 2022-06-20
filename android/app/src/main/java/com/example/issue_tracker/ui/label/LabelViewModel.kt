package com.example.issue_tracker.ui.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.repository.LabelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelViewModel @Inject constructor(
    private val labelRepository: LabelRepository
): ViewModel() {

    private val _labelList = MutableStateFlow<List<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    init {
        viewModelScope.launch {
            labelRepository.getLabelList().collect {
                _labelList.value = it
            }
        }
    }
}