package com.example.it.issuetracker.data.datasource.remote

import com.example.it.issuetracker.data.datasource.LabelDataSource
import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.data.network.IssueTrackerService

private const val TAG = "LabelDataSourceImpl"

class LabelRemoteDataSourceImpl(
    private val issueTrackerApi: IssueTrackerService,
) : LabelDataSource {

    override suspend fun getLabelInfoList(): List<LabelDto> = issueTrackerApi.getLabelInfoList()

    override suspend fun addLabel(addLabelDto: AddLabelDto) {
        issueTrackerApi.addLabelInfo(addLabelDto)
    }

    override suspend fun editLabel(labelDto: LabelDto) {
        val editLabelDto = AddLabelDto(
            labelDto.title,
            labelDto.description,
            labelDto.color,
            labelDto.textColor
        )
        issueTrackerApi.editLabelInfo(labelDto.id, editLabelDto)
    }

    override suspend fun deleteLabelInfo(id: Int) {
        issueTrackerApi.deleteLabelInfo(id)
    }
}