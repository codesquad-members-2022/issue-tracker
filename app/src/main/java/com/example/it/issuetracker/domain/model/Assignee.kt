package com.example.it.issuetracker.domain.model

import java.io.Serializable

data class Assignee(
    val id: Long,
    val githubId: String,
    val imageUrl: String,
) : Serializable
