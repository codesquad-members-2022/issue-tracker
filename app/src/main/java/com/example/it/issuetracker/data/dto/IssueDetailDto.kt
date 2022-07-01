package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.IssueDetail
import com.squareup.moshi.Json

data class IssueDetailDto(
    val id: Long,
    val title: String,
    @Json(name = "status") var issueStatus: Boolean,
    val createdTime: String,
    val description: String,
    val writer: WriterDto,
    @Json(name = "assignees") val manager: List<AssigneeDto>,
    val labels: List<LabelDto>,
    @Json(name = "milestone") val milestones: MilestoneDto,
    var comments: List<CommentDto>,
)

fun IssueDetailDto.toIssueDetail(): IssueDetail =
    IssueDetail(
        id = id,
        title = title,
        issueStatus = issueStatus,
        description = description,
        writer = writer.toWriter(),
        manager = manager.map { it.toAssignee() },
        createdTime = createdTime,
        labels = labels.map { it.toLabel() },
        milestones = milestones.toMilestone(),
        comments = comments.map { it.toComment() }
    )