package com.example.it.issuetracker.domain.repository

interface LoginRepository {

    suspend fun getAccessToken(code: String): String
}