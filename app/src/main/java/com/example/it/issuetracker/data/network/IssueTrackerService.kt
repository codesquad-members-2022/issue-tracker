package com.example.it.issuetracker.data.network

import com.example.it.issuetracker.data.dto.*
import com.example.it.issuetracker.domain.model.IssuesIDList
import retrofit2.http.*

interface IssueTrackerService {

    @GET("issues")
    suspend fun getIssues(): List<IssueDto>

    @GET("issues/{id}")
    suspend fun getIssue(@Path("id") id: Long): IssueDetailDto

    @PATCH("issues")
    suspend fun updateIssueClose(@Query("status") status: Boolean, @Body issueList: IssuesIDList)

    @GET("login/oauth/github")
    suspend fun loginOAuthGithub(@Query("code") code: String): LoginDto

    @GET("labels")
    suspend fun getLabelInfoList(): List<LabelDto>

    @POST("labels")
    suspend fun addLabelInfo(@Body addLabelDto: AddLabelDto)

    @PATCH("labels/{id}")
    suspend fun editLabelInfo(@Path("id") id: Int, @Body addLabelDto: AddLabelDto)

    @DELETE("labels/{id}")
    suspend fun deleteLabelInfo(@Path("id") id: Int)

    @GET("milestones")
    suspend fun getMilestoneInfoList(): List<MilestoneDto>

    @POST("milestones")
    suspend fun addMilestone(@Body addMilestoneDto: AddMilestoneDto)

    @PATCH("milestones/{id}")
    suspend fun editMilestoneInfo(@Path("id") id: Long, @Body addMilestoneDto: AddMilestoneDto)

    @DELETE("milestones/{id}")
    suspend fun deleteMilestone(@Path("id") id: Long)
}