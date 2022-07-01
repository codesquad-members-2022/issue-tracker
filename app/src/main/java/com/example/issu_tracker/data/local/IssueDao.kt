package com.example.issu_tracker.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.issu_tracker.data.IssueList


@Dao
interface IssueDao {
    @Insert
    suspend fun insert(issue: IssueList.Issue)

    @Delete
    suspend fun delete(issue: IssueList.Issue)

    @Query("SELECT * FROM Issue") // 테이블의 모든 값을 가져와라
    suspend fun getAll(): List<IssueList.Issue>

    @Query("DELETE FROM Issue WHERE id = :id")
    suspend fun deleteIssueByName(id: String)

    @Query("DELETE FROM Issue")
    suspend fun deleteAllIssue()
}