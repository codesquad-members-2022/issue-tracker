package com.example.it.issuetracker.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth

interface LoginRepository {
    suspend fun signInWithCredential(auth: FirebaseAuth, credential: AuthCredential): Result<String>

    suspend fun checkUserRegistered(userUid: String): Result<Boolean>

    suspend fun getUserUidList(): Result<List<String>>

    suspend fun registerUser(userList: List<String>): Result<Boolean>
}