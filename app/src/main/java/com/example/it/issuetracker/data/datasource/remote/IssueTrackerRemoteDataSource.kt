package com.example.it.issuetracker.data.datasource.remote

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.dto.IssueDetailDto
import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.data.dto.MemberDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.data.network.IssueTrackerService
import com.example.it.issuetracker.domain.model.Issue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class IssueTrackerRemoteDataSource(
    private val issueTrackerApi: IssueTrackerService,
) : IssueTrackerDataSource {

    override fun getIssue(): Flow<List<IssueDto>> = flow {
        emit(issueTrackerApi.getIssues())
    }

    override suspend fun deleteIssue(list: List<Issue>) {}

    override suspend fun deleteIssue(id: Long) {}

    override suspend fun closeIssue(list: List<Issue>) {}

    override suspend fun closeIssue(id: Long) {}

    override suspend fun revertIssue(list: SortedMap<Int, Issue>) {}

    override suspend fun getMember(): Result<List<MemberDto>> {
        return runCatching { emptyList() }
    }

    override suspend fun getMilestone(): Result<List<MilestoneDto>> {
        return runCatching { emptyList() }
    }

    override suspend fun getFilterList(hm: HashMap<String, Any>): Result<List<IssueDto>> {
        return runCatching { emptyList() }
    }

    override fun findByIssueName(title: String): Flow<List<IssueDto>> = flow {
        emit(emptyList())
    }

    override fun getIssueDetail(id: Long): Flow<IssueDetailDto> = flow {
        emit(IssueDetailDto(id = 0,
            title = "",
            issueStatus = "",
            createdTime = "",
            description = "",
            writer = "",
            manager = "",
            labels = emptyList(),
            milestones = emptyList(),
            comments = emptyList()))
    }

    override suspend fun addLike(id: Long, uid: Long) {}

    override suspend fun addBest(id: Long, uid: Long) {}

    override suspend fun addHate(id: Long, uid: Long) {}

    override suspend fun addOk(id: Long, uid: Long) {}
}