package com.example.issu_tracker.data.repository

interface IssueEditorRepository {

    suspend fun loadLabel(): List<String>
    suspend fun loadAssignee(): List<String>
    suspend fun uploadImageAndGetImageUriFromFireBase(uri: String): String

}