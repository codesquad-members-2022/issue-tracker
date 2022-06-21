package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.data.network.IssueTrackerService
import com.example.it.issuetracker.domain.model.Label
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LabelDataSourceDefaultImpl(
    private val issuerTrackerApi: IssueTrackerService,
) : LabelDataSource {

    private val database = mutableListOf<LabelDto>()

    override fun getLabelInfoList(): Flow<List<LabelDto>> = flow {
        emit(LabelFakeDatabase.getAll())
    }

    override suspend fun addLabel(label: Label) {
        LabelFakeDatabase.add(
            LabelDto(LabelFakeDatabase.size + 1,
                label.name,
                label.description,
                label.color,
                label.textColor)
        )
    }

    override suspend fun deleteLabelInfo(id: Int) {
        // TODO (네트워크 통신을 통해 데이터 삭제하기)

        val labelInfo = database.find { it.id == id }
        database.remove(labelInfo)
    }
}