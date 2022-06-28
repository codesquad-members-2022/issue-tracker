package com.example.it.issuetracker.data.datasource

import android.util.Log
import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.data.network.IssueTrackerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "MilestoneDataSourceImpl"

class MilestoneDataSourceDefaultImpl(
    private val issueTrackerApi: IssueTrackerService,
) : MilestoneDataSource {

    override suspend fun getMilestoneInfoList(): List<MilestoneDto> {
        try {
            return issueTrackerApi.getMilestoneInfoList()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }

    override suspend fun addMilestone(addMilestoneDto: AddMilestoneDto) {
        try {
            issueTrackerApi.addMilestone(addMilestoneDto)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }

    override suspend fun editMilestone(milestoneDto: MilestoneDto) {

    }

    override suspend fun deleteMilestone(id: Long) {
        try {
            issueTrackerApi.deleteMilestone(id)
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            error(e)
        }
    }
}