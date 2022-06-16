package com.example.it.issuetracker.data.network

import com.example.it.issuetracker.data.dto.LoginDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IssueTrackerService {

    @GET("login/oauth/github")
    suspend fun loginOAuthGithub(@Query("code") code: String): LoginDto
}