package com.example.it.issuetracker.presentation.main.milestone

import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.presentation.common.Constants

data class MilestoneUiState(
    val milestoneList: List<MileStone> = listOf(),
    val dataLoading: Boolean = false,
    val editMode: Boolean = false,
    val completeTask: Boolean = false,
    val errorMsgId: Int = Constants.INIT_ERROR_MSG_ID
)
