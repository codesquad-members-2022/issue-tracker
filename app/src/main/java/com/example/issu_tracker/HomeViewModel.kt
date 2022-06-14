package com.example.issu_tracker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _issueList = MutableStateFlow<List<Issue>>(listOf())
    val issueList: StateFlow<List<Issue>> = _issueList

    init {
        val issueDummyData =
            listOf(
                Issue(0, "마일스톤", "Document", "그라운드 룰", "Issue_tracker 의 그라운드 룰을 정의합니다."),
                Issue(1, "마일스톤", "Feature", "리사이클러뷰 구현", "리사이클러뷰를 구현했습니다."),
            )
        _issueList.value = issueDummyData
    }


}