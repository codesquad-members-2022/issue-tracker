package com.team1.issuetracker.ui.main.milestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team1.issuetracker.common.IssueState
import com.team1.issuetracker.common.PrintLog
import com.team1.issuetracker.data.model.Issue
import com.team1.issuetracker.data.model.Label
import com.team1.issuetracker.data.model.Milestone
import com.team1.issuetracker.data.repository.MilestoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MilestoneViewModel @Inject constructor(private val repository: MilestoneRepository) : ViewModel() {

    private val _milestoneList = MutableStateFlow<List<Milestone>>(emptyList())
    val milestoneList = _milestoneList

    private val _itemCount: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val itemCount: StateFlow<Int> = _itemCount

    private val checkedSet: MutableSet<Int> = mutableSetOf()

    private val _closeOrDeleteFlow = MutableSharedFlow<Boolean>(
        replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val closeOrDeleteFlow = _closeOrDeleteFlow.asSharedFlow()

    init {
        getMilestone()
    }

    private fun getMilestone() {
        viewModelScope.launch {
            repository.getMilestone().collect{
                _milestoneList.value = it
            }
        }
    }

    fun checkItem(inx: Int){
        if(checkedSet.contains(inx)) {
            PrintLog.printLog("${inx} -> unchecked")
            checkedSet.remove(inx)
        } else {
            PrintLog.printLog("${inx} -> checked")
            checkedSet.add(inx)
        }
        _itemCount.value = checkedSet.size
    }

    fun checkedSetClear(){
        checkedSet.clear()
        _itemCount.value = 0
        PrintLog.printLog("itemCount, ${itemCount.value}")
    }

    fun requestRemoveMilestoneSet(){
        checkedSet.forEach {
            _milestoneList.value[it].state = false
        }

        val tempList = ArrayList<Milestone>()
        _milestoneList.value.forEach {
            if(it.state) tempList.add(Milestone(it.id, it.title, it.progress, it.content, it.date, it.open, it.closed, false,false, false, true))
        }
        PrintLog.printLog("tempList Size : ${tempList.size}")
        _milestoneList.value = tempList

        viewModelScope.launch {
            _closeOrDeleteFlow.emit(true)
        }

    }

    // 1개 이슈 닫기 - 스와이프 후 삭제 시
    fun requestRemoveSpecificIssue(id: Int){
        PrintLog.printLog("clicked id : ${id}")
        checkedSet.add(id)

        _milestoneList.value[id].state = false
        val tempList = ArrayList<Milestone>()
        _milestoneList.value.forEach {
            if(it.state) tempList.add(Milestone(it.id, it.title, it.progress, it.content, it.date, it.open, it.closed, false,false, false, true))
        }
        PrintLog.printLog("tempList Size : ${tempList.size}")
        _milestoneList.value = tempList

        viewModelScope.launch {
            _closeOrDeleteFlow.emit(true)
        }
    }

    fun undo(){
        checkedSet.forEach {
            _milestoneList.value[it].state = true
        }

        val tempList = ArrayList<Milestone>()
        _milestoneList.value.forEach {
            if(it.state) tempList.add(Milestone(it.id, it.title, it.progress, it.content, it.date, it.open, it.closed, false,false, false, true))
        }
        PrintLog.printLog("tempList Size : ${tempList.size}")
        _milestoneList.value = tempList

        checkedSet.clear()
    }

}