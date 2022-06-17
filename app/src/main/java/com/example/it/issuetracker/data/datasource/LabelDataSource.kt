package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelInfoListDto
import com.example.it.issuetracker.domain.model.Label

interface LabelDataSource {

    suspend fun getLabelInfoList(): LabelInfoListDto

    suspend fun addLabel(label: Label)

    suspend fun deleteLabelInfo(id: Int)
}