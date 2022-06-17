package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.data.dto.LabelInfoListDto
import com.example.it.issuetracker.domain.model.Label

interface LabelRepository {

    suspend fun getLabelInfoList(): LabelInfoListDto

    suspend fun addLabel(label: Label)

    suspend fun deleteLabelInfo(id: Int)
}