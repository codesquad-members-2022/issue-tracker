package com.example.it.issuetracker.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubUserInfoDto(

    @Json(name = "login")
    val login: String,

    @Json(name = "avatar_url")
    val avatarUrl: String
)