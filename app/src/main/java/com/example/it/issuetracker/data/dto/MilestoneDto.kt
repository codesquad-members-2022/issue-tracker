package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.MileStone
import com.squareup.moshi.Json

data class MilestoneDto(
    val id: Long? = null,
    val title: String,
    val description: String? = null,
    @Json(name = "deadLine") val deadline: String? = null,
    val openedIssues: Int? = null,
    val closedIssues: Int? = null,
)

fun MilestoneDto.toMilestone(): MileStone =
    MileStone(
        id = id,
        title = title,
        description = description,
        deadLine = deadline,
        openedIssues = openedIssues,
        closedIssue = closedIssues
    )