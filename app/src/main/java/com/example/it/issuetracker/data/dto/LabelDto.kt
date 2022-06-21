package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.Label
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LabelDto(
    val id: Int,
    val title: String,
    val description: String,
    val color: String,
    val textColor: String,
)

fun LabelDto.toLabel(): Label =
    Label(id = id, title = title, description = description, color = color, textColor = textColor)