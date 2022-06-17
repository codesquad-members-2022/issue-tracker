package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelInfo
import com.example.it.issuetracker.data.dto.LabelInfoListDto

interface LabelDataSource {

    suspend fun getLabelInfoList(): LabelInfoListDto

    suspend fun addLabelInfo(labelInfo: LabelInfo)

    suspend fun deleteLabelInfo(id: Int)
}