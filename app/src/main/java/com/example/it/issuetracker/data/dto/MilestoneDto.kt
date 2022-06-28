package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.MileStone

data class MilestoneDto(
    val id: Long,
    val title: String,
    val description: String? = null,
    val startDate: String? = null,
    val deadLine: String? = null,
    val openedIssue: Int? = null,
    val closedIssue: Int? = null,
)

fun MilestoneDto.toMilestone(): MileStone =
    MileStone(
        id = id,
        title = title,
        description = description,
        startDate = startDate,
        deadLine = deadLine,
        openedIssue = openedIssue,
        closedIssue = closedIssue
    )