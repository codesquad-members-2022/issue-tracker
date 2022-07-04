package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.IssueDetail
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.presentation.main.issue.register.NewIssue
import kotlinx.coroutines.flow.Flow

interface IssueTrackerRepository {

    fun getIssue(): Flow<List<Issue>>

    suspend fun updateIssueDelete(status: Boolean, list: List<Long>)

    suspend fun updateIssueClose(status: Boolean, list: List<Long>)

    suspend fun getWriter(): Result<List<Member>>

    suspend fun getMilestone(): Result<List<MileStone>>

    suspend fun getFilterList(value: HashMap<String, Any>): Result<List<Issue>>

    fun findIssue(title: String): Flow<List<Issue>>

    fun getIssueDetail(id: Long): Flow<IssueDetail>

    suspend fun saveIssue(newIssue: NewIssue): Result<Unit>

    suspend fun addLike(id: Long, uid: Long)

    suspend fun addBest(id: Long, uid: Long)

    suspend fun addHate(id: Long, uid: Long)

    suspend fun addOk(id: Long, uid: Long)

    suspend fun addComment(id: Long, text: String)
}