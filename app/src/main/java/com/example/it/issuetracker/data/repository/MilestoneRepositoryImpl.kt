package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.MilestoneDataSource
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import kotlinx.coroutines.flow.Flow

class MilestoneRepositoryImpl(
    private val dataSource: MilestoneDataSource
) : MilestoneRepository {

    override fun getMilestoneInfoList(): Flow<List<MilestoneDto>> {
        return dataSource.getMilestoneInfoList()
    }

    override suspend fun addMilestone(milestoneDto: MilestoneDto) {
        dataSource.addMilestone(milestoneDto)
    }

    override suspend fun editMilestone(milestoneDto: MilestoneDto) {
        dataSource.editMilestone(milestoneDto)
    }

    override suspend fun deleteMilestone(id: Long) {
        dataSource.deleteMilestone(id)
    }
}