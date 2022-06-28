package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.MilestoneDataSource
import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.domain.repository.MilestoneRepository

class MilestoneRepositoryImpl(
    private val dataSource: MilestoneDataSource,
) : MilestoneRepository {

    override suspend fun getMilestoneInfoList(): Result<List<MilestoneDto>> {
        return runCatching { dataSource.getMilestoneInfoList() }
    }

    override suspend fun addMilestone(addMilestoneDto: AddMilestoneDto): Result<Unit> {
        return runCatching { dataSource.addMilestone(addMilestoneDto) }
    }

    override suspend fun editMilestone(milestoneDto: MilestoneDto): Result<Unit> {
        return runCatching { dataSource.editMilestone(milestoneDto) }
    }

    override suspend fun deleteMilestone(id: Long): Result<Unit> {
        return runCatching { dataSource.deleteMilestone(id) }
    }
}