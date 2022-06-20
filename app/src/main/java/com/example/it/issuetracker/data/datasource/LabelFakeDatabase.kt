package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelDto
import kotlinx.coroutines.delay

object LabelFakeDatabase {

    private val database = mutableListOf<LabelDto>()

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
        database.add(index, labelDto)
        database.remove(data)
    }

    suspend fun delete(id: Int) {
        val labelInfo = database.find { it.id == id }
        database.remove(labelInfo)
    }
}