package com.example.issue_tracker.ui.milestone

import androidx.lifecycle.ViewModel
import com.example.issue_tracker.common.timeStampToDateString
import com.example.issue_tracker.model.MileStone
import com.example.issue_tracker.repository.MileStoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MileStoneAddViewModel @Inject constructor(
    private val repository: MileStoneRepository
): ViewModel() {

    private val _mileStone = MutableStateFlow(MileStone(null, "", "", INITIAL_DATE))
    val mileStone = _mileStone.asStateFlow()

    val mileStoneTitle: MutableStateFlow<String> = MutableStateFlow("")

    val mileStoneDescription: MutableStateFlow<String> = MutableStateFlow("")

    fun onPickedDate(timeStamp: Long) {
        val timeStampFormatted = timeStampToDateString(timeStamp)
        _mileStone.value = MileStone(null, mileStoneTitle.value, mileStoneDescription.value, timeStampFormatted)
    }

    fun saveData() {
        if(_mileStone.value.dueDate == INITIAL_DATE) {
            repository.addLabelList(MileStone(null, mileStoneTitle.value, mileStoneDescription.value, INITIAL_DATE))
            return
        }
        repository.addLabelList(_mileStone.value)
    }

    companion object {
        private const val INITIAL_DATE = "선택사항 (yyyy-mm-dd)"
    }

}