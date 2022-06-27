package com.example.issue_tracker.ui.issue

import androidx.lifecycle.ViewModel
import com.example.issue_tracker.common.addElement
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.model.MileStone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class IssueAddViewModel @Inject constructor() : ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    private val _mileStoneList = MutableStateFlow<MutableList<MileStone>>(mutableListOf())
    val mileStoneList = _mileStoneList.asStateFlow()

    private val _labelChoose = MutableStateFlow(defaultLabel)
    val labelChoose = _labelChoose.asStateFlow()

    private val _mileStoneChoose = MutableStateFlow(defaultMileStone)
    val mileStoneChoose = _mileStoneChoose.asStateFlow()

    init {
        addDummyData()
    }

    private fun addDummyData() {
        _labelList.addElement(Label(1, "feature", "Contents1", "#FFFFFF"))
        _labelList.addElement(Label(2, "fix", "Contents2", "#FFFFFF"))
        _mileStoneList.addElement(MileStone(1, "코코아 코스", "Contents1", "2022-06-13"))
        _mileStoneList.addElement(MileStone(1, "마스터즈 코스", "Contents2", "2022-06-13"))
    }

    fun findClickedLabelMenu(id: Int) {
        val clickedMenu = labelList.value[id].copy()
        _labelChoose.value = clickedMenu
    }

    fun findClickedMileStoneMenu(id: Int) {
        val clickedMenu = mileStoneList.value[id].copy()
        _mileStoneChoose.value = clickedMenu
    }

    companion object {
        val defaultLabel = Label(null, null, null, null)
        val defaultMileStone = MileStone(null, null, null, null)
    }
}