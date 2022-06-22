package com.example.it.issuetracker.domain.model

import com.example.it.issuetracker.presentation.main.issue.list.Mode
import java.io.Serializable

data class MileStone(
    val id: Long,
    val title: String,
    val description: String? = null,
    val startDate: String? = null,
    val deadLine: String? = null,
    val openedIssue: Int? = null,
    val closedIssue: Int? = null,
    var isChecked: Boolean = false,
    val mode: Mode = Mode.DEFAULT
) : Serializable