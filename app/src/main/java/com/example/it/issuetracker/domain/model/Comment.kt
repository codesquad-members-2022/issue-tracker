package com.example.it.issuetracker.domain.model

data class Comment(
    val id: Long,
    val githubId: String,
    val imageUrl: String,
    val content: String,
    val createDate: String,
    val like: Int,
    val hate: Int,
    val best: Int,
    val ok: Int,
)
