package com.example.it.issuetracker.presentation.main.label

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.data.dto.LabelInfo
import com.example.it.issuetracker.domain.repository.LabelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "LabelViewModel"

class LabelViewModel(
    private val labelRepository: LabelRepository
) : ViewModel() {

    private var _labelList = MutableStateFlow<List<LabelInfo>>(emptyList())
    val labelList = _labelList.asStateFlow()

    private var _dataLoading = MutableStateFlow(false)
    val dataLoading = _dataLoading.asStateFlow()

    fun getLabelInfoList() {
        viewModelScope.launch {
            _dataLoading.value = true
            val labelInfoList = labelRepository.getLabelInfoList()
            _labelList.value = labelInfoList.labelInfo
            _dataLoading.value = false
        }
    }
}