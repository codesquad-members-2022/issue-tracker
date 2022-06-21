package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.domain.model.Label
import kotlinx.coroutines.flow.Flow

interface LabelRepository {

    fun getLabelInfoList(): Flow<List<LabelDto>>

    suspend fun addLabel(label: Label)

    suspend fun deleteLabelInfo(id: Int)
}