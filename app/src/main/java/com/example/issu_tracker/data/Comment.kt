package com.example.issu_tracker.data

import java.io.Serializable

data class Comment(
    val user: User? = null,
    val content: String = "",
    val time: String = ""
) : Serializable