package com.example.issu_tracker.data.repository

import com.example.issu_tracker.data.Label
import com.example.issu_tracker.data.User

interface FilterRepository {

    suspend fun loadLabel(): Map<String, Label>
    suspend fun loadUsers(): Map<String, User>

}