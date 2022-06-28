package com.example.it.issuetracker.presentation.main.label

import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.common.Constants

data class LabelUiState(
    val labelList: List<Label> = listOf(),
    val dataLoading: Boolean = false,
    val editMode: Boolean = false,
    val completeTask: Boolean = false,
    val errorMsgId: Int = Constants.INIT_ERROR_MSG_ID
)
