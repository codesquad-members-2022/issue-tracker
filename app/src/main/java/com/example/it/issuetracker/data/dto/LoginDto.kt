package com.example.it.issuetracker.data.dto

import com.example.it.issuetracker.domain.model.LoginInformation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginDto(

    @Json(name = "jwt")
    val jwt: String,

    @Json(name = "imageUrl")
    val imageUrl: String,
)


fun LoginDto.toLoginInformation(): LoginInformation =
    LoginInformation(jwt = jwt, imageUrl = imageUrl)