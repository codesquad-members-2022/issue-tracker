package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.MileStone

data class IssueDto(
    val id: Long,
    val title: String,
    val description: String,
    var state: Boolean,
    val label: List<LabelDto>,
    val mileStone: MileStone,
    val createdTime: String,
    val milestoneId: Long,
    val memberId: Long,
)

fun IssueDto.toIssue(): Issue =
    Issue(
        id = id,
        title = title,
        description = description,
        state = state,
        label = label.map { it.toLabel() },
        mileStone = mileStone
    )
