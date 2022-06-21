package com.example.it.issuetracker.domain.model

import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.presentation.main.issue.Mode
import java.io.Serializable

data class Label(
    val id: Int,
    val title: String,
    val description: String = "",
    val color: String,
    val textColor: String,
    var isChecked: Boolean = false,
    val mode: Mode = Mode.DEFAULT,
) : Serializable

fun Label.toLabelDto(): LabelDto {
    return LabelDto(id, title, description, color, textColor)
}