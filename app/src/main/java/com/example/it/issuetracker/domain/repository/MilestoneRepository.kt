package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.data.dto.MilestoneDto
import kotlinx.coroutines.flow.Flow

interface MilestoneRepository {

    fun getMilestoneInfoList(): Flow<List<MilestoneDto>>

    suspend fun addMilestone(milestoneDto: MilestoneDto)

    suspend fun editMilestone(milestoneDto: MilestoneDto)

    suspend fun deleteMilestone(id: Long)
}