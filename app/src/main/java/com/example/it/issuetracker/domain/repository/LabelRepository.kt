package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.data.dto.LabelDto

interface LabelRepository {

    suspend fun getLabelInfoList(): Result<List<LabelDto>>

    suspend fun addLabel(addLabelDto: AddLabelDto): Result<Unit>

    suspend fun editLabel(labelDto: LabelDto): Result<Unit>

    suspend fun deleteLabelInfo(id: Int): Result<Unit>
}