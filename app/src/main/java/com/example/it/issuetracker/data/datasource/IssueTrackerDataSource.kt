package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.IssueDto
import com.example.it.issuetracker.domain.model.Issue

interface IssueTrackerDataSource {

    suspend fun getIssue(): Result<List<IssueDto>>
    suspend fun deleteIssue(list: List<Issue>): Result<List<IssueDto>>
    suspend fun closeIssue(list: List<Issue>): Result<List<IssueDto>>
}