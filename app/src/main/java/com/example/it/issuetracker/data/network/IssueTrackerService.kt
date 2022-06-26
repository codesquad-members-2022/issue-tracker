package com.example.it.issuetracker.data.network

import com.example.it.issuetracker.data.dto.AddLabelDto
import com.example.it.issuetracker.data.dto.LabelDto
import com.example.it.issuetracker.data.dto.LoginDto
import retrofit2.http.*

interface IssueTrackerService {

    @GET("login/oauth/github")
    suspend fun loginOAuthGithub(@Query("code") code: String): LoginDto

    @GET("labels")
    suspend fun getLabelInfoList(): List<LabelDto>

    @POST("labels")
    suspend fun addLabelInfo(@Body addLabelDto: AddLabelDto)

    @DELETE("labels/{id}")
    suspend fun deleteLabelInfo(@Path("id") id: Int)
}