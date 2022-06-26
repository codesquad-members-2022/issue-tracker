package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.data.dto.LabelDto

interface LabelDataSource {

    suspend fun getLabelInfoList(): List<LabelDto>

    suspend fun addLabel(addLabelDto: AddLabelDto)

    suspend fun editLabel(labelDto: LabelDto)

    suspend fun deleteLabelInfo(id: Int)
}