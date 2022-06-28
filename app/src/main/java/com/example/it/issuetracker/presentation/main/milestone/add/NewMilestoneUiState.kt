package com.example.it.issuetracker.presentation.main.milestone.add

import com.example.it.issuetracker.presentation.common.Constants

data class NewMilestoneUiState(
    val title: String = "",
    val description: String = "",
    val deadline: String = "",
    val completeTask: Boolean = false,
    val errorMsgId: Int = Constants.INIT_ERROR_MSG_ID,
)
