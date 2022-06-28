package com.example.it.issuetracker.data.network

import com.example.it.issuetracker.data.dto.*
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

    @GET("milestones")
    suspend fun getMilestoneInfoList(): List<MilestoneDto>

    @POST("milestones")
    suspend fun addMilestone(@Body addMilestoneDto: AddMilestoneDto)

    @DELETE("milestones/{id}")
    suspend fun deleteMilestone(@Path("id") id: Long)
}