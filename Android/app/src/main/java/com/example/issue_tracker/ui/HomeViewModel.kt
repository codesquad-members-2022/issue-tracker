package com.example.issue_tracker.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issue_tracker.domain.model.IssueState
import com.example.issue_tracker.domain.model.Label
import com.example.issue_tracker.domain.model.MileStone
import com.example.issue_tracker.domain.model.User
import com.example.issue_tracker.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())
    val labelList: StateFlow<List<Label>> = _labelList

    private val _mileStoneList = MutableStateFlow<List<MileStone>>(listOf())
    val mileStoneList: StateFlow<List<MileStone>> = _mileStoneList

    private val _userList = MutableStateFlow<List<User>>(listOf())
    val userList: StateFlow<List<User>> = _userList

    private val _editCheckList = MutableStateFlow<MutableList<Int>>(mutableListOf())
    val editCheckList: StateFlow<List<Int>> = _editCheckList

    private val _labelEditMode = MutableStateFlow<Boolean>(false)
    val labelEditMode: StateFlow<Boolean> = _labelEditMode

    private val _stateList = MutableStateFlow<List<IssueState>>(listOf())
    val stateList: StateFlow<List<IssueState>> = _stateList

    init {
        loadLabelList()
        loadMileStoneList()
        makeDummyUser()
        initStateList()
    }

     fun loadLabelList(){
        viewModelScope.launch {
            _labelList.emit(repository.getLabelList().toMutableList())
        }
    }

    fun loadMileStoneList(){
        viewModelScope.launch {
            _mileStoneList.emit(repository.getMileStoneList())
        }
    }

    private fun initStateList() {
        _stateList.value = listOf(
            IssueState.OPEN,
            IssueState.WRITE_MYSELF,
            IssueState.ASSIGN_MYSELF,
            IssueState.WRITE_COMMENT,
            IssueState.CLOSE
        )
    }

    private fun makeDummyUser() {
        val users = mutableListOf<User>()
        for (i in 0..10) {
            users.add(User(i, "User${i}", "https://images.unsplash.com/photo-1655057011043-158c48f3809d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDEyfHhqUFI0aGxrQkdBfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60")) }
        _userList.value = users
    }

    fun clearCheckList() {
        _editCheckList.value.clear()
    }

    fun addCheckList(itemId: Int) {
        _editCheckList.value.add(itemId)
    }

    fun removeCheckList(itemId: Int) {
        _editCheckList.value.removeIf {
            it == itemId
        }
    }

    fun removeLabelList() {
        for (i in 0 until _editCheckList.value.size) {
            _labelList.value.removeIf {
                it.id == _editCheckList.value[i]
            }
        }
        _labelEditMode.value = false
    }

    fun labelEditModeOn() {
        _labelEditMode.value = true
    }

    fun labelEditModeOff() {
        _labelEditMode.value = false
    }
}