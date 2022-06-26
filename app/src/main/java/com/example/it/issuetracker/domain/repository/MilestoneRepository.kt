package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import kotlinx.coroutines.flow.Flow

interface MilestoneRepository {

    suspend fun getMilestoneInfoList(): Result<List<MilestoneDto>>

    suspend fun addMilestone(addMilestoneDto: AddMilestoneDto): Result<Unit>

    suspend fun editMilestone(milestoneDto: MilestoneDto): Result<Unit>

    suspend fun deleteMilestone(id: Long): Result<Unit>
}