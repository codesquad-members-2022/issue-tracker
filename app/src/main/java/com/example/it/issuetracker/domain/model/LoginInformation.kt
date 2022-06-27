package com.example.it.issuetracker.domain.model

data class LoginInformation(
    val jwt: String,
//    val id: Long, TODO(서버에서 id값 내려주면 주석 해제)
    val imageUrl: String,
)
