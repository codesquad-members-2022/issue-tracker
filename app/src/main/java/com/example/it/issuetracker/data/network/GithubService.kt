package com.example.it.issuetracker.data.network

import com.example.it.issuetracker.data.dto.GithubTokenDto
import retrofit2.http.*

interface GithubService {

    @POST("access_token")
    suspend fun getAccessToken(
        @Header("Accept") accept: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
    ): GithubTokenDto
}