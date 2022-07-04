package com.example.it.issuetracker.presentation.main.issue.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.it.issuetracker.R
import com.example.it.issuetracker.data.datasource.local.UserSharedPrefDataSource
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.presentation.common.Constants.INIT_ERROR_MSG_ID
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilterViewModel(
    private val labelRepository: LabelRepository,
    private val issueRepository: IssueTrackerRepository,
    private val sharedPref: UserSharedPrefDataSource,
) : ViewModel() {

    private val _labelList = MutableStateFlow<List<LabelDto>>(emptyList())
    val labelList = _labelList.asStateFlow()
    private val _labelIndex = MutableStateFlow<Int>(0)
    val labelIndex = _labelIndex.asStateFlow()

    private val _issues = MutableStateFlow<List<Issue>>(emptyList())
    val issues = _issues.asStateFlow()

    private val _eventApply = MutableSharedFlow<Boolean>()
    val eventApply = _eventApply.asSharedFlow()

    private val _state = MutableStateFlow<List<String>>(
        listOf(
            "선택",
            "열린 이슈",
            "내가 작성한 이슈",
            "나에게 할당한 이슈",
            "내가 댓글을 남긴 이슈",
            "닫힌 이슈"
        )
    )
    val state = _state.asStateFlow()
    private val _stateIndex = MutableStateFlow<Int>(0)
    val stateIndex = _stateIndex.asStateFlow()

    private val _writerList = MutableStateFlow<List<Member>>(emptyList())
    val writerList = _writerList.asStateFlow()
    private val _writerIndex = MutableStateFlow<Int>(0)
    val writerIndex = _writerIndex.asStateFlow()

    private val _milestoneList = MutableStateFlow<List<MileStone>>(emptyList())
    val milestoneList = _milestoneList.asStateFlow()
    private val _milestoneIndex = MutableStateFlow<Int>(0)
    val milestoneIndex = _milestoneIndex.asStateFlow()

    private val _filtering = MutableStateFlow<HashMap<String, Any>>(hashMapOf())

    private val _errorMsgId = MutableStateFlow<Int>(INIT_ERROR_MSG_ID)
    val errorMsgId = _errorMsgId.asStateFlow()

    init {
        getLabelInfoList()
        getWriter()
        getMilestone()
    }

    private fun getLabelInfoList() = viewModelScope.launch {
        labelRepository.getLabelInfoList().map {
            val list = it.toMutableList()
            list.add(0, LabelDto(0, "선택", "", "#FFFFFF", "#007AFF"))
            _labelList.value = list
        }
    }

    private fun getWriter() = viewModelScope.launch {
        issueRepository.getWriter()
            .onSuccess { writers ->
                val writerList = writers.toMutableList()
                writerList.add(0, Member(0, "선택", ""))
                _writerList.value = writerList
            }
            .onFailure {
                _errorMsgId.value = R.string.network_error
            }
    }

    private fun getMilestone() = viewModelScope.launch {
        issueRepository.getMilestone()
            .onSuccess { milestones ->
                val milestoneList = milestones.toMutableList()
                milestoneList.add(0, MileStone(0, "선택", ""))
                _milestoneList.value = milestoneList
            }
            .onFailure {
                _errorMsgId.value = R.string.network_error
            }

    }

    fun getFilterList() = viewModelScope.launch {
        if (_filtering.value.isEmpty()) {
            _eventApply.emit(false)
            return@launch
        }
        issueRepository.getFilterList(_filtering.value)
            .onSuccess { filterList ->
                _issues.value = filterList
                _eventApply.emit(true)
            }
            .onFailure {
                _errorMsgId.value = R.string.network_error
            }
    }

    fun clickFilterItem(item: String, type: FilterType, position: Int) {
        val userId = sharedPref.getData("id")
        when (type) {
            FilterType.STATE -> {
                _stateIndex.value = position
                when (item) {
                    "열린 이슈", "닫힌 이슈" -> {
                        _filtering.value["status"] = item == "열린 이슈"
                        _filtering.value.remove("writerId")
                        _filtering.value.remove("assignee")
                        _filtering.value.remove("commentedBy")
                    }
                    "내가 작성한 이슈" -> {
                        _filtering.value["writerId"] = userId
                        _filtering.value.remove("status")
                        _filtering.value.remove("assignee")
                        _filtering.value.remove("commentedBy")
                    }
                    "내게 할당된 이슈" -> {
                        _filtering.value["assignee"] = userId
                        _filtering.value.remove("status")
                        _filtering.value.remove("writerId")
                        _filtering.value.remove("commentedBy")
                    }
                    "내가 댓글 남긴 이슈" -> {
                        _filtering.value["commentedBy"] = userId
                        _filtering.value.remove("status")
                        _filtering.value.remove("writerId")
                        _filtering.value.remove("assignee")
                    }
                    "선택" -> {
                        _filtering.value.remove("status")
                        _filtering.value.remove("writerId")
                        _filtering.value.remove("assignee")
                        _filtering.value.remove("commentedBy")
                    }
                }
            }
            FilterType.WRITER -> {
                _writerIndex.value = position
                if (item == "선택") {
                    _filtering.value.remove("writerId")
                } else {
                    val id = _writerList.value.find { it.name == item }?.id ?: return
                    _filtering.value["writerId"] = id
                }
            }
            FilterType.LABEL -> {
                _labelIndex.value = position
                if (item == "선택") {
                    _filtering.value.remove("label")
                } else {
                    val id = _labelList.value.find { it.title == item }?.id ?: return
                    _filtering.value["label"] = id
                }
            }
            FilterType.MILESTONE -> {
                _milestoneIndex.value = position
                if (item == "선택") {
                    _filtering.value.remove("milestone")
                } else {
                    val id = _milestoneList.value.find { it.title == item }?.id ?: return
                    _filtering.value["milestone"] = id
                }
            }
        }
    }
}
