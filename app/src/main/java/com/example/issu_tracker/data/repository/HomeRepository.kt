package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Issue

interface HomeRepository {
    // TODO  추후 반환타입 수정
    suspend fun loadIssues(): List<Issue>
    suspend fun updateIssueState(itemId: String, boolean: Boolean)
    suspend fun deleteIssueList(itemId: String)

    suspend fun updateIssueList(list: List<Issue>, boolean: Boolean)
    suspend fun updateIssueState(list: List<Issue>, boolean: Boolean)
}