package com.example.issu_tracker.data.local

import androidx.room.*
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.User


@Dao
interface IssueDao {
    @Insert
    suspend fun insert(issue: Issue)

    @Delete
    suspend fun delete(issue: Issue)

    @Query("SELECT * FROM Issue") // 테이블의 모든 값을 가져와라
    suspend fun getAll(): List<Issue>

    @Query("DELETE FROM Issue WHERE id = :id")
    suspend fun deleteIssueByName(id: String)

    @Query("DELETE FROM Issue")
    suspend fun deleteAllIssue()
}