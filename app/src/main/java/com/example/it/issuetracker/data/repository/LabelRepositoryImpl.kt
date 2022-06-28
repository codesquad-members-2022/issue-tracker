package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.LabelDataSource
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.domain.repository.LabelRepository
import kotlinx.coroutines.flow.Flow

class LabelRepositoryImpl(
    private val dataSource: LabelDataSource,
) : LabelRepository {
    override fun getLabelInfoList(): Flow<List<LabelDto>> {
        return dataSource.getLabelInfoList()
    }

    override suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String,
    ) {
        dataSource.addLabel(title, description, backgroundColor, textColor)
    }

    override suspend fun editLabel(labelDto: LabelDto) {
        dataSource.editLabel(labelDto)
    }

    override suspend fun deleteLabelInfo(id: Int) {
        dataSource.deleteLabelInfo(id)
    }
}