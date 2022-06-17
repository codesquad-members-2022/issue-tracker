package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.LabelDataSource
import com.example.it.issuetracker.data.dto.LabelInfoListDto
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.repository.LabelRepository

class LabelRepositoryImpl(
    private val dataSource: LabelDataSource,
) : LabelRepository {
    override suspend fun getLabelInfoList(): LabelInfoListDto {
        return dataSource.getLabelInfoList()
    }

    override suspend fun addLabel(label: Label) {
        dataSource.addLabel(label)
    }

    override suspend fun deleteLabelInfo(id: Int) {
        dataSource.deleteLabelInfo(id)
    }
}