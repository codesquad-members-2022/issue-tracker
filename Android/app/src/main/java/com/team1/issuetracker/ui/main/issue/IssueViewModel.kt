package com.team1.issuetracker.ui.main.issue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team1.issuetracker.common.IssueState
import com.team1.issuetracker.common.PrintLog
import com.team1.issuetracker.data.model.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(): ViewModel() {

    private val _itemCount: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val itemCount: StateFlow<Int> = _itemCount

    private val _issueList: MutableStateFlow<List<Issue>> = MutableStateFlow<List<Issue>>(emptyList())
    val issueList: StateFlow<List<Issue>> = _issueList

    private val sampleOriginIssueList = ArrayList<Issue>()
    private val checkedSet: MutableSet<Int> = mutableSetOf()

    private val _closeOrDeleteFlow = MutableSharedFlow<Boolean>(
        replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val closeOrDeleteFlow = _closeOrDeleteFlow.asSharedFlow()

    init {
        addSampleIssueData()
    }

    fun checkItem(inx: Int){
        if(checkedSet.contains(inx)) checkedSet.remove(inx)
        else checkedSet.add(inx)

        _itemCount.value = checkedSet.size
    }

    fun checkedSetClear(){
        checkedSet.clear()
        _itemCount.value = 0
        PrintLog.printLog("itemCount, ${itemCount.value}")
    }

    // 1개 이슈 닫기 - 스와이프 후 삭제 시
    fun requestCloseSpecificIssue(id: Int){
        PrintLog.printLog("clicked id : ${id}")
        sampleOriginIssueList[id].issueState = IssueState.CloseRequested
        val tempList = ArrayList<Issue>()
        sampleOriginIssueList.forEach {
            if(it.issueState == IssueState.Open) tempList.add(Issue(it.issueId, it.mileStone, it.title, it.content, it.labelContent, it.labelColor, false, false, false, IssueState.Open))
        }
        PrintLog.printLog("tempList Size : ${tempList.size}")
        _issueList.value = tempList

        viewModelScope.launch {
            _closeOrDeleteFlow.emit(true)
        }
    }

    fun undo(){
        
    }

    private fun addSampleIssueData(){

        val tempList = ArrayList<Issue>()

        //sampleOriginIssueList.add(Issue(-2, "마일스톤0", "title 0", "content 0", "label0", "", false, false, false, IssueState.CloseRequested))
        //sampleOriginIssueList.add(Issue(-1, "마일스톤1", "title 1", "content 1", "label1", "", false, false, false, IssueState.CloseRequested))
        for(i in 0..15){
            sampleOriginIssueList.add(Issue(i, "마일스톤$i", "title $i", "content $i", "label$i", ""))
        }

        sampleOriginIssueList.forEach {
            if(it.issueState == IssueState.Open) tempList.add(it)
        }

        _issueList.value = tempList
    }
}