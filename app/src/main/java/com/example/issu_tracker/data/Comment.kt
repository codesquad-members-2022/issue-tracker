package com.example.issu_tracker.data

data class Comment(
    val commentUser: User?=null, val content: String="",
    val time: String=""
)