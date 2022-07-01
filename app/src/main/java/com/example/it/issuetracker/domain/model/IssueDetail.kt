package com.example.it.issuetracker.domain.model

import java.io.Serializable

data class IssueDetail(
    val id: Long,
    val title: String,
    val description: String,
    val issueStatus: Boolean,
    val writer: Writer,
    val manager: List<Assignee>,
    val createdTime: String,
    val labels: List<Label>,
    val milestones: MileStone,
    val comments: List<Comment>,
) : Serializable
