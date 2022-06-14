package com.example.it.issuetracker.data.network

import com.example.it.issuetracker.data.dto.GithubUserInfoDto
import retrofit2.http.GET
import retrofit2.http.Header

interface GithubApiService {

    @GET("user")
    suspend fun getUserInfo(
        @Header("Authorization") authorization: String,
    ): GithubUserInfoDto
}