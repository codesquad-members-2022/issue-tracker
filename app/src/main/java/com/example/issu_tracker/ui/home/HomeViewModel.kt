package com.example.issu_tracker.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issu_tracker.data.FilterCondition
import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.data.network.NetworkResult
import com.example.issu_tracker.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _issueListStateFlow = MutableStateFlow<NetworkResult<List<IssueList>>>(NetworkResult.Loading())
    val issueListStateFlow: StateFlow<NetworkResult<List<IssueList>>> = _issueListStateFlow

    private val _filteredIssueListStateFlow = MutableStateFlow<NetworkResult<List<IssueList>>>(NetworkResult.Loading())
    val filteredIssueListStateFlow: StateFlow<NetworkResult<List<IssueList>>> = _filteredIssueListStateFlow

    init {
        viewModelScope.launch {
            getIssueList()
        }
    }

    suspend fun getIssueList() {
            val issueList = repository.loadFirstPageIssues()
            if (issueList is NetworkResult.Success) {
               issueList.data.toMutableList().add(IssueList.IssueProgressBar)
                _issueListStateFlow.value = issueList
            }
    }

    fun getNextIssueList(currentPage: Int) {
        viewModelScope.launch {
            val issueListResponse = _issueListStateFlow.value
            if (issueListResponse is NetworkResult.Success) {
                val issueList = issueListResponse.data.toMutableList()
                issueList.removeLast()
                issueList.addAll(repository.loadNextPageIssues(currentPage))
                issueList.add(IssueList.IssueProgressBar)
                _issueListStateFlow.value = NetworkResult.Success(issueList)
            }
        }
    }

    suspend fun updateIssueSate(itemId: String, boolean: Boolean) {
        repository.updateIssueState(itemId, boolean)
        getIssueList()
    }

    suspend fun deleteIssueList(issueList: List<IssueList>) {
        repository.deleteIssueList(issueList)
        getIssueList()
        Log.d("updateDelte", issueList.toString())
    }

    suspend fun updateIssueList(issueList: List<IssueList>, boolean: Boolean) {
        repository.updateIssueListState(issueList, boolean)
        getIssueList()
        Log.d("updateDelte", issueList.toString())
    }

    fun filterIssueList(condition: FilterCondition) {
        if (_issueListStateFlow.value is NetworkResult.Success) {
            val filteredIssueList = (_issueListStateFlow.value as NetworkResult.Success).data
                .filter {
                    if (condition.state.isNotEmpty() && it is IssueList.Issue) {
                        it.state
                    } else {
                        true
                    }
                }.filter {
                    if (condition.writer.isNotEmpty() && it is IssueList.Issue) {
                        it.user.name == condition.writer
                    } else {
                        true
                    }
                }
                .filter {
                if (it is IssueList.Issue) {
                    it.label.any { label ->
                        if (condition.label.isNotEmpty()) {
                            label.content == condition.label
                        } else {
                            true
                        }
                    }
                }
                false
            }
            
        _filteredIssueListStateFlow.value = NetworkResult.Success(filteredIssueList)
        
        }
    }
}




