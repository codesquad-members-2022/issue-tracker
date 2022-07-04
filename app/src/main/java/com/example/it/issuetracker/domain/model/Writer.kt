package com.example.it.issuetracker.domain.model

import java.io.Serializable

data class Writer(
    val id: Long,
    val name: String,
    val email: String?,
    val githubId: String,
    val imageUrl: String,
) : Serializable