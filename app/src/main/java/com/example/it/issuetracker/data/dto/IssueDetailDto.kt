package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.IssueDetail

data class IssueDetailDto(
    val id: Long,
    val title: String,
    val issueStatus: String,
    val createdTime: String,
    val description: String,
    val writer: String,
    val manager: String,
    val labels: List<LabelDto>,
    val milestones: List<MilestoneDto>,
    val comments: List<CommentDto>,
)

fun IssueDetailDto.toIssueDetail(): IssueDetail =
    IssueDetail(
        id = id,
        title = title,
        issueStatus = issueStatus,
        description = description,
        writer = writer,
        manager = manager,
        createdTime = createdTime,
        labels = labels.map { it.toLabel() },
        milestones = milestones.map { it.toMilestone() },
        comments = comments.map { it.toComment() }
    )