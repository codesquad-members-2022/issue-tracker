package com.team1.issuetracker.ui.main.milestone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team1.issuetracker.data.model.Label
import com.team1.issuetracker.data.model.Milestone
import com.team1.issuetracker.data.repository.MilestoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MilestoneViewModel @Inject constructor(private val repository: MilestoneRepository) : ViewModel() {

    private val _milestoneList = MutableStateFlow<List<Milestone>>(emptyList())
    val milestoneList = _milestoneList

    init {
        getLabel()
    }

    private fun getLabel() {
        viewModelScope.launch {
            repository.getMilestone().collect{
                _milestoneList.value = it
            }
        }
    }

}