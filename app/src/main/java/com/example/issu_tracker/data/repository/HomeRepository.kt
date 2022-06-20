package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Issue

interface HomeRepository {
    // TODO  추후 반환타입 수정
    suspend fun loadIssues(): List<Issue>
    suspend fun deleteIssue(itemId: String)
}