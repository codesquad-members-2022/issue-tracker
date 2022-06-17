package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.domain.model.Issue

interface IssueTrackerRepository {

    suspend fun getIssue(): Result<List<Issue>>
}