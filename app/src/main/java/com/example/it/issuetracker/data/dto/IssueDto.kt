package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.MileStone

data class IssueDto(
    val id: Long,
    val title: String,
    val description: String,
    var state: Boolean,
    val label: List<Label>,
    val mileStone: MileStone,
)

fun IssueDto.toIssue(): Issue =
    Issue(id = id, title = title, description = description, label = label, mileStone = mileStone)
