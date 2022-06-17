package com.example.it.issuetracker.domain.model

data class Label(
    val name: String,
    val description: String = "",
    val color: String,
    val textColor: String,
)