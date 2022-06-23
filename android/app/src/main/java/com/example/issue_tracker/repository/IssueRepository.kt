package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Issue
import kotlinx.coroutines.flow.Flow

interface IssueRepository {

    suspend fun getIssue(): Flow<MutableList<Issue>>
}