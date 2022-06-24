package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.data.network.IssueTrackerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MilestoneDataSourceDefaultImpl(
    private val issueTrackerApi: IssueTrackerService
) : MilestoneDataSource {

    override fun getMilestoneInfoList(): Flow<List<MilestoneDto>> = flow {
        emit(MilestoneFakeDatabase.getAll())
    }

    override suspend fun addMilestone(title: String, description: String, deadline: String) {
        MilestoneFakeDatabase.add(
            MilestoneDto(
                MilestoneFakeDatabase.size + 1,
                title,
                description,
                "",
                deadline,
                null,
                null
            )
        )
    }

    override suspend fun editMilestone(milestoneDto: MilestoneDto) {
        MilestoneFakeDatabase.edit(milestoneDto)
    }

    override suspend fun deleteMilestone(id: Long) {
        MilestoneFakeDatabase.delete(id)
    }
}