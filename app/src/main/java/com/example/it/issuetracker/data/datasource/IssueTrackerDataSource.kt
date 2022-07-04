package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.IssueDetailDto
import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.data.dto.MemberDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.domain.model.IssueDetail
import com.example.it.issuetracker.presentation.main.issue.register.NewIssue
import kotlinx.coroutines.flow.Flow

interface IssueTrackerDataSource {

    fun getIssue(): Flow<List<IssueDto>>
    suspend fun updateIssueDelete(status: Boolean, list: List<Long>)
    suspend fun updateIssueClose(status: Boolean, list: List<Long>)
    suspend fun getMember(): Result<List<MemberDto>>
    suspend fun getMilestone(): Result<List<MilestoneDto>>
    suspend fun getFilterList(value: HashMap<String, Any>): Result<List<IssueDto>>
    fun findByIssueName(title: String): Flow<List<IssueDto>>
    fun getIssueDetail(id: Long): Flow<IssueDetailDto>
    suspend fun saveIssue(newIssue: NewIssue)
    suspend fun addLike(id: Long, uid: Long)
    suspend fun addBest(id: Long, uid: Long)
    suspend fun addHate(id: Long, uid: Long)
    suspend fun addOk(id: Long, uid: Long)
    suspend fun addComment(id: Long, text: String)
}