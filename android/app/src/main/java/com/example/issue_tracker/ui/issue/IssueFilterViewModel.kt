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
class IssueFilterViewModel @Inject constructor() : ViewModel() {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList = _labelList.asStateFlow()

    private val _mileStoneList = MutableStateFlow<MutableList<MileStone>>(mutableListOf())
    val mileStoneList = _mileStoneList.asStateFlow()

    // 사실 LiveData 여도 상관 없는 데다가 flow{...} 처럼 Flow 빌더가 없이 사용하는 것이 괜찮을까?
    private val _statusChoose = MutableStateFlow("열린 이슈")
    val statusChoose = _statusChoose.asStateFlow()

    private val _writerChoose = MutableStateFlow("선택")
    val writerChoose = _writerChoose.asStateFlow()

    private val _labelChoose = MutableStateFlow("선택")
    val labelChoose = _labelChoose.asStateFlow()

    private val _mileStoneChoose = MutableStateFlow("선택")
    val mileStoneChoose = _mileStoneChoose.asStateFlow()

    fun addDummyData() {
        _labelList.addElement(Label(1, "feature", "Contents1", "#FFFFFF"))
        _labelList.addElement(Label(2, "fix", "Contents2", "#FFFFFF"))
        _mileStoneList.addElement(MileStone(1, "코코아 코스", "Contents1", "2022-06-13"))
        _mileStoneList.addElement(MileStone(1, "마스터즈 코스", "Contents2", "2022-06-13"))
    }

    fun setStatusChoose(choose: String) {
        _statusChoose.value = choose
    }

    fun setWriterChoose(choose: String) {
        _writerChoose.value = choose
    }

    fun setLabelChoose(choose: String) {
        _labelChoose.value = choose
    }

    fun setMileStoneChoose(choose: String) {
        _mileStoneChoose.value = choose
    }
}