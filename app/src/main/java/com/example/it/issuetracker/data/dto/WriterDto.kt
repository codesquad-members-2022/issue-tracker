package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Writer

data class WriterDto(
    val id: Long,
    val name: String,
    val email: String?,
    val githubId: String,
    val imageUrl: String,
)

fun WriterDto.toWriter(): Writer =
    Writer(
        id = id,
        name = name,
        email = email,
        githubId = githubId,
        imageUrl = imageUrl
    )