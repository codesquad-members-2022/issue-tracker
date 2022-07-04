package com.example.it.issuetracker.data.datasource.remote

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.dto.IssueDetailDto
import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.data.dto.MemberDto
import com.example.it.issuetracker.data.dto.MilestoneDto
import com.example.it.issuetracker.data.network.IssueTrackerService
import com.example.it.issuetracker.domain.model.IssuesIDList
import com.example.it.issuetracker.presentation.main.issue.register.NewIssue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IssueTrackerRemoteDataSource(
    private val issueTrackerApi: IssueTrackerService,
) : IssueTrackerDataSource {

    override fun getIssue(): Flow<List<IssueDto>> = flow {
        emit(issueTrackerApi.getIssues())
    }

    override suspend fun updateIssueDelete(status: Boolean, list: List<Long>) = Unit

    override suspend fun saveIssue(newIssue: NewIssue) = Unit

    override suspend fun getMember(): Result<List<MemberDto>> {
        return runCatching { emptyList() }
    }

    override suspend fun updateIssueClose(status: Boolean, list: List<Long>) {
        issueTrackerApi.updateIssueClose(status = status, issueList = IssuesIDList(list))
    }

    override suspend fun getMilestone(): Result<List<MilestoneDto>> = runCatching { emptyList() }

    override suspend fun getFilterList(hm: HashMap<String, Any>): Result<List<IssueDto>> =
        runCatching { emptyList() }

    override fun findByIssueName(title: String): Flow<List<IssueDto>> = flow { emit(emptyList()) }

    override fun getIssueDetail(id: Long): Flow<IssueDetailDto> =
        flow { emit(issueTrackerApi.getIssue(id)) }

    override suspend fun addLike(id: Long, uid: Long) {}

    override suspend fun addBest(id: Long, uid: Long) {}

    override suspend fun addHate(id: Long, uid: Long) {}

    override suspend fun addOk(id: Long, uid: Long) {}

    override suspend fun addComment(id: Long, text: String) {}

}