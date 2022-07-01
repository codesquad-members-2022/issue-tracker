package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Issue
import com.squareup.moshi.Json

data class IssueDto(
    val id: Long,
    val title: String,
    val description: String,
    var state: Boolean = true,
    val labels: List<LabelDto>,
    @Json(name = "milestoneName") val mileStone: String,
    val createdTime: String,
)

fun IssueDto.toIssue(): Issue =
    Issue(
        id = id,
        title = title,
        description = description,
        state = state,
        label = labels.map { it.toLabel() },
        mileStone = mileStone
    )
