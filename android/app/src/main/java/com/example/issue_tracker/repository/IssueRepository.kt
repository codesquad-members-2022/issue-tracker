package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Issue

interface IssueRepository {

    suspend fun getDummyIssue(): MutableList<Issue>

    suspend fun getIssue(): MutableList<Issue>
}