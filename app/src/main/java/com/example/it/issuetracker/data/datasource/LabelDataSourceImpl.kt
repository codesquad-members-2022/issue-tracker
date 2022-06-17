package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelInfo
import com.example.it.issuetracker.data.dto.LabelInfoListDto
import com.example.it.issuetracker.data.network.IssueTrackerService

class LabelDataSourceImpl(
    private val issuerTrackerApi: IssueTrackerService
) : LabelDataSource {

    private val database = mutableListOf<LabelInfo>()

    override suspend fun getLabelInfoList(): LabelInfoListDto {
        // TODO (네트워크 통신을 통해 데이터 가져오기)

//        return LabelInfoListDto(database.toList())
        return LabelInfoListDto(LabelFakeDatabase.getAll())
    }

    override suspend fun addLabelInfo(labelInfo: LabelInfo) {
        // TODO (네트워크 통신을 통해 데이터 추가하기)

        LabelFakeDatabase.add(labelInfo)
    }

    override suspend fun deleteLabelInfo(id: Int) {
        // TODO (네트워크 통신을 통해 데이터 삭제하기)

        val labelInfo = database.find { it.id == id }
        database.remove(labelInfo)
    }
}