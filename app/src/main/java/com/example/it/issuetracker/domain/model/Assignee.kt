package com.example.it.issuetracker.domain.model

data class Assignee(
    val id: Long,
    val githubId: String,
    val imageUrl: String,
)
