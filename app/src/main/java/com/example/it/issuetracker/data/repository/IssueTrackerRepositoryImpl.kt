package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.dto.toIssue
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository

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
}