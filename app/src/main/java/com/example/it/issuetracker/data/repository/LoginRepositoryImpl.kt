package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.config.GITHUB_CLIENT_ID
import com.example.it.issuetracker.config.GITHUB_SECRET_ID
import com.example.it.issuetracker.data.network.GithubService
import com.example.it.issuetracker.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val githubApi: GithubService,
) : LoginRepository {
    override suspend fun getAccessToken(code: String): String {
        val token = githubApi.getAccessToken(
            "application/json",
            GITHUB_CLIENT_ID,
            GITHUB_SECRET_ID,
            code
        )
        return token.accessToken
    }
}