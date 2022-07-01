package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Assignee

data class AssigneeDto(
    val id: Long,
    val githubId: String,
    val imageUrl: String,
)

fun AssigneeDto.toAssignee(): Assignee =
    Assignee(
        id = id,
        githubId = githubId,
        imageUrl = imageUrl
    )