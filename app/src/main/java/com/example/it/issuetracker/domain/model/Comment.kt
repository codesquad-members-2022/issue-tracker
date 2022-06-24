package com.example.it.issuetracker.domain.model

data class Comment(
    val id: String,
    val imageUrl: String,
    val content: String,
    val createDate: String,
    val reaction: Int,
)
