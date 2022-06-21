package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.MileStone

data class MilestoneDto(
    val id: Long,
    val title: String,
    val description: String,
    val deadLine: String,
    val issueId: Long,
)

fun MilestoneDto.toMilestone(): MileStone = MileStone(id = id, name = title, deadLine = deadLine)