package com.example.it.issuetracker.domain.model

data class LoginInformation(
    val jwt: String,
    val id: Long,
    val imageUrl: String,
)
