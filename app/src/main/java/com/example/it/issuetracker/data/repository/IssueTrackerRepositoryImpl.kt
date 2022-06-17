package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository

class IssueTrackerRepositoryImpl(
    private val issueTrackerDataSource: IssueTrackerDataSource,
) : IssueTrackerRepository {
    override suspend fun getIssue(): Result<List<Issue>> {
        return issueTrackerDataSource.getIssue()
    }
}