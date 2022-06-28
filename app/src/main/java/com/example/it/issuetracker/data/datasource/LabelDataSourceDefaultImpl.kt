package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.data.network.IssueTrackerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LabelDataSourceDefaultImpl(
    private val issuerTrackerApi: IssueTrackerService,
) : LabelDataSource {

    override fun getLabelInfoList(): Flow<List<LabelDto>> = flow {
        emit(LabelFakeDatabase.getAll())
    }

    override suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String,
    ) {
        LabelFakeDatabase.add(
            LabelDto(
                LabelFakeDatabase.size + 1,
                title,
                description,
                backgroundColor,
                textColor
            )
        )
    }

    override suspend fun editLabel(labelDto: LabelDto) {
        LabelFakeDatabase.edit(labelDto)
    }

    override suspend fun deleteLabelInfo(id: Int) {
        LabelFakeDatabase.delete(id)
    }
}