package com.example.issue_tracker.network

import com.example.issue_tracker.model.IssueDTO
import com.example.issue_tracker.model.LabelListDTO
import retrofit2.http.GET

interface APIService {

    @GET("issues")
    suspend fun getIssues(): IssueDTO

    @GET("labels")
    suspend fun getLabels(): LabelListDTO

}