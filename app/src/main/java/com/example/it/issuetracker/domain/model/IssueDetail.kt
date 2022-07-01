package com.example.it.issuetracker.domain.model

data class IssueDetail(
    val id: Long,
    val title: String,
    val description: String,
    val issueStatus: String,
    val writer: String,
    val manager: String,
    val createdTime: String,
    val labels: List<Label>,
    val milestones: List<MileStone>,
    val comments: List<Comment>,
)
