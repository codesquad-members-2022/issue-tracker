package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.LabelDataSource
import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.domain.repository.LabelRepository

class LabelRepositoryImpl(
    private val dataSource: LabelDataSource,
) : LabelRepository {
    override suspend fun getLabelInfoList(): Result<List<LabelDto>> {
        return runCatching { dataSource.getLabelInfoList() }
    }

    override suspend fun addLabel(addLabelDto: AddLabelDto): Result<Unit> {
        return runCatching {
            dataSource.addLabel(addLabelDto)
        }
    }

    override suspend fun editLabel(labelDto: LabelDto): Result<Unit> {
        return runCatching { dataSource.editLabel(labelDto) }
    }

    override suspend fun deleteLabelInfo(id: Int): Result<Unit> {
        return runCatching { dataSource.deleteLabelInfo(id) }
    }
}