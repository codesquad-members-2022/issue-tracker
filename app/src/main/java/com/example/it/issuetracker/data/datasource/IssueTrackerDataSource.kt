package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.IssueDetailDto
import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.data.dto.MemberDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.domain.model.Issue
import kotlinx.coroutines.flow.Flow
import java.util.*

interface IssueTrackerDataSource {

    suspend fun getIssue(): Result<List<IssueDto>>
    suspend fun deleteIssue(list: List<Issue>): Result<List<IssueDto>>
    suspend fun closeIssue(list: List<Issue>): Result<List<IssueDto>>
    suspend fun revertIssue(list: SortedMap<Int, Issue>): Result<List<IssueDto>>
    suspend fun getMember(): Result<List<MemberDto>>
    suspend fun getMilestone(): Result<List<MilestoneDto>>
    suspend fun getFilterList(value: HashMap<String, Any>): Result<List<IssueDto>>
    fun findByIssueName(title: String): Flow<List<IssueDto>>
    fun getIssueDetail(id: Long): Flow<IssueDetailDto>
}