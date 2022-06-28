package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.dto.toIssue
import com.example.it.issuetracker.data.dto.toMember
import com.example.it.issuetracker.data.dto.toMilestone
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class IssueTrackerRepositoryImpl(
    private val issueTrackerDataSource: IssueTrackerDataSource,
) : IssueTrackerRepository {
    override suspend fun getIssue(): Result<List<Issue>> {
        return issueTrackerDataSource.getIssue().map { issues ->
            issues.map { issue -> issue.toIssue() }
        }
    }

    override suspend fun deleteIssue(list: List<Issue>): Result<List<Issue>> {
        return issueTrackerDataSource.deleteIssue(list).map { issues ->
            issues.map { issue -> issue.toIssue() }

        }
    }

    override suspend fun closeIssue(list: List<Issue>): Result<List<Issue>> {
        return issueTrackerDataSource.closeIssue(list).map { issues ->
            issues.map { issue -> issue.toIssue() }
        }
    }

    override suspend fun revertIssue(list: SortedMap<Int, Issue>): Result<List<Issue>> {
        return issueTrackerDataSource.revertIssue(list).map { issues ->
            issues.map { issue -> issue.toIssue() }
        }
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
}