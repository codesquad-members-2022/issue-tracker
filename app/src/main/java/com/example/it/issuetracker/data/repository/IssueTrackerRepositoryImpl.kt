package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.dto.toIssue
import com.example.it.issuetracker.data.dto.toIssueDetail
import com.example.it.issuetracker.data.dto.toMember
import com.example.it.issuetracker.data.dto.toMilestone
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.IssueDetail
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import com.example.it.issuetracker.presentation.main.issue.register.NewIssue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IssueTrackerRepositoryImpl(
    private val issueTrackerDataSource: IssueTrackerDataSource,
    private val issueRemoteDataSource: IssueTrackerDataSource,
) : IssueTrackerRepository {
    override fun getIssue(): Flow<List<Issue>> {
        return issueRemoteDataSource.getIssue().map { issues ->
            issues.map { issue -> issue.toIssue() }
        }
    }

    override suspend fun updateIssueDelete(status: Boolean, list: List<Long>) {
        issueRemoteDataSource.updateIssueDelete(status, list)
    }

    override suspend fun updateIssueClose(status: Boolean, list: List<Long>) {
        issueRemoteDataSource.updateIssueClose(status, list)
    }

    override suspend fun getWriter(): Result<List<Member>> {
        return issueTrackerDataSource.getMember().map { members ->
            members.map { member -> member.toMember() }
        }
    }

    override suspend fun getMilestone(): Result<List<MileStone>> {
        return issueTrackerDataSource.getMilestone().map { milestones ->
            milestones.map { milestone -> milestone.toMilestone() }
        }
    }

    override suspend fun getFilterList(value: HashMap<String, Any>): Result<List<Issue>> {
        return issueTrackerDataSource.getFilterList(value).map { issues ->
            issues.map { issue -> issue.toIssue() }
        }
    }

    override fun findIssue(title: String): Flow<List<Issue>> {
        return issueTrackerDataSource.findByIssueName(title).map {
            it.map { issue -> issue.toIssue() }
        }
    }

    override fun getIssueDetail(id: Long): Flow<IssueDetail> {
        return issueRemoteDataSource.getIssueDetail(id).map { issueDetail ->
            issueDetail.toIssueDetail()
        }
    }

    override suspend fun saveIssue(newIssue: NewIssue): Result<Unit> {
        return runCatching { issueTrackerDataSource.saveIssue(newIssue) }
    }

    override suspend fun addLike(id: Long, uid: Long) {
        issueTrackerDataSource.addLike(id, uid)
    }

    override suspend fun addBest(id: Long, uid: Long) {
        issueTrackerDataSource.addBest(id, uid)
    }

    override suspend fun addHate(id: Long, uid: Long) {
        issueTrackerDataSource.addHate(id, uid)
    }

    override suspend fun addOk(id: Long, uid: Long) {
        issueTrackerDataSource.addOk(id, uid)
    }

    override suspend fun addComment(id: Long, text: String) {
        issueTrackerDataSource.addComment(id, text)
    }
}