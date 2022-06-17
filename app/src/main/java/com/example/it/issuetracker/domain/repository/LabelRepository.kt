package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.data.dto.LabelInfo
import com.example.it.issuetracker.data.dto.LabelInfoListDto

interface LabelRepository {

    suspend fun getLabelInfoList(): LabelInfoListDto

    suspend fun addLabelInfo(labelInfo: LabelInfo)

    suspend fun deleteLabelInfo(id: Int)
}