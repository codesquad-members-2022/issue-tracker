package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelDto
import kotlinx.coroutines.flow.Flow

interface LabelDataSource {

    fun getLabelInfoList(): Flow<List<LabelDto>>

    suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String
    )

    suspend fun editLabel(labelDto: LabelDto)

    suspend fun deleteLabelInfo(id: Int)
}