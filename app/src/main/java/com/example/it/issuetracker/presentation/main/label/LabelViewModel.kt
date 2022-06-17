package com.example.it.issuetracker.presentation.main.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.domain.repository.LabelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LabelViewModel(
    private val labelRepository: LabelRepository,
) : ViewModel() {

    private var _labelList = MutableStateFlow<List<LabelDto>>(emptyList())
    val labelList = _labelList.asStateFlow()

    private var _dataLoading = MutableStateFlow(false)
    val dataLoading = _dataLoading.asStateFlow()

    fun getLabelInfoList() {
        viewModelScope.launch {
            _dataLoading.value = true
            val labelInfoList = labelRepository.getLabelInfoList()
            _labelList.value = labelInfoList.labelDto
            _dataLoading.value = false
        }
    }
}