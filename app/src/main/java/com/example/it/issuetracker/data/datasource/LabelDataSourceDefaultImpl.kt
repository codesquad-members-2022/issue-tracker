package com.example.it.issuetracker.data.datasource

import android.util.Log
import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.data.network.IssueTrackerService

private const val TAG = "LabelDataSourceImpl"

class LabelDataSourceDefaultImpl(
    private val issueTrackerApi: IssueTrackerService,
) : LabelDataSource {

    override suspend fun getLabelInfoList(): List<LabelDto> {
        try {
            return issueTrackerApi.getLabelInfoList()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }

    override suspend fun addLabel(addLabelDto: AddLabelDto) {
        try {
            issueTrackerApi.addLabelInfo(addLabelDto)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }

    override suspend fun editLabel(labelDto: LabelDto) {
        try {
            val editLabelDto = AddLabelDto(
                labelDto.title,
                labelDto.description,
                labelDto.color,
                labelDto.textColor
            )
            issueTrackerApi.editLabelInfo(labelDto.id, editLabelDto)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }

    override suspend fun deleteLabelInfo(id: Int) {
        try {
            issueTrackerApi.deleteLabelInfo(id)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }
}