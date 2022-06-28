package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.AddMilestoneDto
import com.example.it.issuetracker.data.dto.MilestoneDto

interface MilestoneDataSource {

    suspend fun getMilestoneInfoList(): List<MilestoneDto>

    suspend fun addMilestone(addMilestoneDto: AddMilestoneDto)

    suspend fun editMilestone(milestoneDto: MilestoneDto)

    suspend fun deleteMilestone(id: Long)
}