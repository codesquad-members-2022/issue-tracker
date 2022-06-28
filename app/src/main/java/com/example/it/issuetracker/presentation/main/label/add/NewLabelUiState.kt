package com.example.it.issuetracker.presentation.main.label.add

import com.example.it.issuetracker.presentation.common.Constants

data class NewLabelUiState(
    val title: String = "",
    val description: String = "",
    val color: String = "#B71C1C",
    val textColor: String = "#FFFFFFFF",
    val completeTask: Boolean = false,
    val errorMsgId: Int = Constants.INIT_ERROR_MSG_ID
)
