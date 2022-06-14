package com.example.it.issuetracker.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubTokenDto(

    @Json(name = "access_token")
    val accessToken: String,

    @Json(name = "scope")
    val scope: String,

    @Json(name = "token_type")
    val tokenType: String,
)
