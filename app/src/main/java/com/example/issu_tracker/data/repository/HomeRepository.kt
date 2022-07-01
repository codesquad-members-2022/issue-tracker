package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.IssueList

interface HomeRepository {

    suspend fun loadFirstPageIssues(): List<IssueList>
    suspend fun loadNextPageIssues(currentNumber: Int): List<IssueList>
    suspend fun updateIssueState(itemId: String, boolean: Boolean)
    suspend fun deleteIssueList(list: List<IssueList>)
    suspend fun updateIssueListState(list: List<IssueList>, boolean: Boolean)

}