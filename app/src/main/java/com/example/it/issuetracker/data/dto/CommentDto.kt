package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Comment

data class CommentDto(
    val uid: Long,
    val id: String,
    val imageUrl: String,
    val content: String,
    val createDate: String,
    val reaction: Int,
    var like: Int,
    var best: Int,
    var hate: Int,
    var ok: Int,
)

fun CommentDto.toComment(): Comment =
    Comment(uid, id, imageUrl, content, createDate, reaction, like, hate, best, ok)