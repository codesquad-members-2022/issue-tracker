package com.example.issu_tracker.data.repository

interface FilterRepository {

    suspend fun loadLabel(): List<String>
    suspend fun loadUsers(): List<String>

}