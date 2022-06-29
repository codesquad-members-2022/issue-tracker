package com.example.it.issuetracker.domain.repository

import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.domain.model.IssueDetail
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone
import kotlinx.coroutines.flow.Flow
import java.util.*

interface IssueTrackerRepository {

    suspend fun getIssue(): Flow<List<Issue>>

    suspend fun deleteIssue(list: List<Issue>)

    suspend fun deleteIssue(id: Long)

    suspend fun closeIssue(list: List<Issue>)

    suspend fun closeIssue(id: Long)

    suspend fun revertIssue(list: SortedMap<Int, Issue>)

    suspend fun getWriter(): Result<List<Member>>

    suspend fun getMilestone(): Result<List<MileStone>>

    suspend fun getFilterList(value: HashMap<String, Any>): Result<List<Issue>>

    fun findIssue(title: String): Flow<List<Issue>>

    fun getIssueDetail(id: Long): Flow<IssueDetail>

    suspend fun addLike(id: Long, uid: Long)

    suspend fun addBest(id: Long, uid: Long)

    suspend fun addHate(id: Long, uid: Long)

    suspend fun addOk(id: Long, uid: Long)
}