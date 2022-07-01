package com.example.it.issuetracker.domain.model

data class Writer(
    val id: Long,
    val name: String,
    val email: String?,
    val githubId: String,
    val imageUrl: String,
)