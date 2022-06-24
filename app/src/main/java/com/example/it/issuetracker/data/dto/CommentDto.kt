package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Comment

data class CommentDto(
    val id: String,
    val imageUrl: String,
    val content: String,
    val createDate: String,
    val reaction: Int,
)

fun CommentDto.toComment(): Comment = Comment(id, imageUrl, content, createDate, reaction)