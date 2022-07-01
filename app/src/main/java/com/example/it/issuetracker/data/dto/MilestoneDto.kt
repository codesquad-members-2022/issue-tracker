package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.MileStone

data class MilestoneDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    val startDate: String? = null,
    val deadline: String? = null,
    val openedIssues: Int? = null,
    val closedIssues: Int? = null,
)

fun MilestoneDto.toMilestone(): MileStone =
    MileStone(
        id = id,
        title = title,
        description = description,
        startDate = startDate,
        deadLine = deadline,
        openedIssues = openedIssues,
        closedIssue = closedIssues
    )