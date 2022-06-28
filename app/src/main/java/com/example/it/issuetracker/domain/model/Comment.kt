package com.example.it.issuetracker.domain.model

data class Comment(
    val uid: Long,
    val id: String,
    val imageUrl: String,
    val content: String,
    val createDate: String,
    val reaction: Int,
    val like: Int,
    val hate: Int,
    val best: Int,
    val ok: Int,
)
