package com.example.issu_tracker.ui.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.ConditionType
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.Label
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.repository.IssueEditorRepository
import com.example.issu_tracker.ui.common.SpinnerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueEditorViewModel @Inject constructor(val repository: IssueEditorRepository) :
    ViewModel(), SpinnerViewModel {

    private val _issueTitleStateFlow = MutableStateFlow<String>("")
    val issueTitleStateFlow = _issueTitleStateFlow.asStateFlow()

    private val _issueBodyStateFlow = MutableStateFlow<String>("")
    val issueBodyStateFlow = _issueBodyStateFlow.asStateFlow()

    private val _assigneeMap = mutableMapOf<String, User>()
    private val _assigneeStateFlow = MutableStateFlow<List<String>>(mutableListOf(""))
    val assigneeStateFlow = _assigneeStateFlow.asStateFlow()

    private val _labelMap = mutableMapOf<String, Label>()
    private val _labelStateFlow = MutableStateFlow<List<String>>(mutableListOf(""))
    val labelStateFlow = _labelStateFlow.asStateFlow()

    private val _mileStoneStateFlow = MutableStateFlow<List<String>>(mutableListOf(""))
    val mileStoneStateFlow = _mileStoneStateFlow.asStateFlow()

    private val _imageUrlFromFireBaseStateFlow = MutableStateFlow<String>("")
    val imageUrlFromFireBaseStateFlow = _imageUrlFromFireBaseStateFlow.asStateFlow()

    private var assigneeConditionValue: User = User()
    private var labelConditionValue: Label = Label()
    private var mileStoneConditionValue = ""

    init {
        viewModelScope.launch {
            setLabelList()
            setAssigneeList()
        }
    }

    fun inputIssueTitleText(text: String) {
        _issueTitleStateFlow.value = text
    }

    fun inputIssueBodyText(text: String) {
        _issueBodyStateFlow.value = text
    }

    fun createIssue() {
        val newIssue = Issue(
            id = "",
            comments = listOf(),
            description = _issueBodyStateFlow.value,
            label = listOf(labelConditionValue),
            mileStone = "마일스톤",
            state = true,
            title = _issueTitleStateFlow.value,
            user = assigneeConditionValue
        )
        viewModelScope.launch {
            repository.createNewIssue(newIssue)
        }
    }

    private suspend fun setLabelList() {
        _labelMap.putAll(repository.loadLabel())
        val labelList = _labelMap.keys.toMutableList()
        labelList.add("")
        _labelStateFlow.emit(labelList)
    }

    private suspend fun setAssigneeList() {
        _assigneeMap.putAll(repository.loadAssignee())
        val assigneeList = _assigneeMap.keys.toMutableList()
        assigneeList.add("")
        _assigneeStateFlow.emit(assigneeList)
    }

    override fun inputSpinnerValue(condition: String, conditionType: Enum<ConditionType>) {
        when (conditionType) {
            ConditionType.ASSIGNEE -> assigneeConditionValue = _assigneeMap[condition] ?: User()
            ConditionType.LABEL -> labelConditionValue = _labelMap[condition] ?: Label()
//            Constants.CONDITION_TYPE_ASSIGNEE -> mileStoneConditionValue = text
        }
    }

    fun uploadImageInFireBase(imageUriFromLocal: String) {
        viewModelScope.launch {
            val imageUrl = repository.uploadImageAndGetImageUriFromFireBase(imageUriFromLocal)
            _imageUrlFromFireBaseStateFlow.value = imageUrl
        }
    }
}

