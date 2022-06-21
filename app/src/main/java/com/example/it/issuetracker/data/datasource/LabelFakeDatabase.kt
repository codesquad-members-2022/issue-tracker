package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelDto
import kotlinx.coroutines.delay

object LabelFakeDatabase {

    private val database = mutableListOf<LabelDto>()

    val size = database.size

    suspend fun add(labelDto: LabelDto) {
        delay(1000)
        database.add(labelDto)
    }

    suspend fun getAll(): List<LabelDto> {
        delay(1000)
        return database.toList()
    }
}