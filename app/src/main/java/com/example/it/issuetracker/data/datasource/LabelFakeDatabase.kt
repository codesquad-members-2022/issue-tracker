package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelDto
import kotlinx.coroutines.delay

object LabelFakeDatabase {

    val database = mutableListOf(
        LabelDto(1, "feature", "Feature 1", "#B71C1C", "#FFFFFFFF"),
        LabelDto(2, "documentation", "documentation 1", "#F57F17", "#FFFFFFFF"),
        LabelDto(3, "bug", "bug 1", "#FFEE58", "#FF000000"),
        LabelDto(4, "fix", "fix 1", "#0288D1", "#FF000000"),
    )

    val size: Int
        get() = database.size

    suspend fun add(labelDto: LabelDto) {
        delay(1000)
        database.add(labelDto)
    }

    suspend fun getAll(): List<LabelDto> {
        delay(1000)
        return database.toList()
    }

    suspend fun edit(labelDto: LabelDto) {
        delay(1000)
        val data = database.find { it.id == labelDto.id }
        val index = database.indexOf(data)
        database[index] = labelDto
    }

    suspend fun delete(id: Int) {
        val labelInfo = database.find { it.id == id }
        database.remove(labelInfo)
    }
}