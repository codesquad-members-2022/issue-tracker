package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Comment
import com.squareup.moshi.Json

data class CommentDto(
    val id: Long,
    val githubId: String,
    val imageUrl: String,
    val content: String,
    @Json(name = "createdTime") val createDate: String,
    var like: Int = 0,
    var best: Int = 0,
    var hate: Int = 0,
    var ok: Int = 0,
)

fun CommentDto.toComment(): Comment =
    Comment(id, githubId, imageUrl, content, createDate, like, hate, best, ok)