package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.MilestoneDto
import kotlinx.coroutines.delay

object MilestoneFakeDatabase {

    val database = mutableListOf(
        MilestoneDto(
            id = 1,
            title = "마일스톤",
            deadline = "2022-06-14",
            description = "",
            openedIssue = 1,
            closedIssue = 1
        ),
        MilestoneDto(
            id = 2,
            title = "마스터즈 코스",
            deadline = "2022-06-16",
            description = ""
        ),
        MilestoneDto(
            id = 3,
            title = "테스트 그룹",
            deadline = "2022-06-17",
            description = "",
            openedIssue = 3,
            closedIssue = 2
        ),
        MilestoneDto(
            id = 4,
            title = "마스터즈 코스 숫자",
            deadline = null,
            description = ""
        )
    )

    val size: Long
        get() = database.size.toLong()

    suspend fun add(milestoneDto: MilestoneDto) {
        delay(1000)
        database.add(milestoneDto)
    }

    suspend fun getAll(): List<MilestoneDto> {
        delay(1000)
        return database.toList()
    }

    suspend fun edit(milestoneDto: MilestoneDto) {
        delay(1000)
        val data = database.find { it.id == milestoneDto.id }
        val index = database.indexOf(data)
        database[index] = milestoneDto
    }

    suspend fun delete(id: Long) {
        val milestoneInfo = database.find { it.id == id }
        database.remove(milestoneInfo)
    }
}