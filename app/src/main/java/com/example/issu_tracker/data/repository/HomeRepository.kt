package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.User
import com.example.issu_tracker.data.network.NetworkResult

interface HomeRepository {

    suspend fun loadIssues(): NetworkResult<List<Issue>>
    suspend fun updateIssueState(itemId: String, boolean: Boolean)

    suspend fun deleteIssueList(list: List<Issue>)

    suspend fun updateIssueListState(list: List<Issue>, boolean: Boolean)
}