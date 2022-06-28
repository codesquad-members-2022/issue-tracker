package com.example.it.issuetracker.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddMilestoneDto(
    val title: String,
    val description: String,
    val deadline: String
)
