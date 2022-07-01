package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.data.Label
import com.example.issu_tracker.data.User

interface IssueEditorRepository {

    suspend fun loadLabel(): Map<String, Label>
    suspend fun loadAssignee(): Map<String, User>
    fun createNewIssue(newIssue: IssueList)
    suspend fun uploadImageAndGetImageUriFromFireBase(uri: String): String

}