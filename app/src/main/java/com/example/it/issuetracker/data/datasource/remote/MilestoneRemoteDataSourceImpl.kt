package com.example.it.issuetracker.data.datasource.remote

import com.example.it.issuetracker.data.datasource.MilestoneDataSource
import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.data.network.IssueTrackerService

private const val TAG = "MilestoneDataSourceImpl"

class MilestoneRemoteDataSourceImpl(
    private val issueTrackerApi: IssueTrackerService,
) : MilestoneDataSource {

    override suspend fun getMilestoneInfoList(): List<MilestoneDto> =
        issueTrackerApi.getMilestoneInfoList()

    override suspend fun addMilestone(addMilestoneDto: AddMilestoneDto) {
        issueTrackerApi.addMilestone(addMilestoneDto)
    }

    override suspend fun editMilestone(milestoneDto: MilestoneDto) {
        val editMilestoneDto = AddMilestoneDto(
            milestoneDto.title,
            milestoneDto.description ?: "",
            milestoneDto.deadline ?: ""
        )
        issueTrackerApi.editMilestoneInfo(milestoneDto.id!!, editMilestoneDto)
    }

    override suspend fun deleteMilestone(id: Long) {
        issueTrackerApi.deleteMilestone(id)
    }
}