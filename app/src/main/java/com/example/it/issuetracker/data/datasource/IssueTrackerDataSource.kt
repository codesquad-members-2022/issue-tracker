package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.IssueDetailDto
import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.data.dto.MemberDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.domain.model.Issue
import kotlinx.coroutines.flow.Flow
import java.util.*

interface IssueTrackerDataSource {

    suspend fun getIssue(): Flow<List<IssueDto>>
    suspend fun deleteIssue(list: List<Issue>)
    suspend fun deleteIssue(id: Long)
    suspend fun closeIssue(list: List<Issue>)
    suspend fun closeIssue(id: Long)
    suspend fun revertIssue(list: SortedMap<Int, Issue>)
    suspend fun getMember(): Result<List<MemberDto>>
    suspend fun getMilestone(): Result<List<MilestoneDto>>
    suspend fun getFilterList(value: HashMap<String, Any>): Result<List<IssueDto>>
    fun findByIssueName(title: String): Flow<List<IssueDto>>
    fun getIssueDetail(id: Long): Flow<IssueDetailDto>
    suspend fun addLike(id: Long, uid: Long)
    suspend fun addBest(id: Long, uid: Long)
    suspend fun addHate(id: Long, uid: Long)
    suspend fun addOk(id: Long, uid: Long)
}