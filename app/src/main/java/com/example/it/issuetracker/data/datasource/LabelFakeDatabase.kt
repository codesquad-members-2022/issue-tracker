package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelInfo
import kotlinx.coroutines.delay

object LabelFakeDatabase {

    private val database = mutableListOf<LabelInfo>()

    val size = database.size

    suspend fun add(labelInfo: LabelInfo) {
        delay(1000)
        database.add(labelInfo)
    }

    suspend fun getAll(): List<LabelInfo> {
        delay(1000)
        return database.toList()
    }
}