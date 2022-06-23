package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.User

interface HomeRepository {

    suspend fun loadIssues(): List<Issue>
    suspend fun updateIssueState(itemId: String, boolean: Boolean)

    suspend fun deleteIssueList(list: List<Issue>)

    suspend fun updateIssueListState(list: List<Issue>, boolean: Boolean)

    suspend fun loadFriendList(): List<User>
}