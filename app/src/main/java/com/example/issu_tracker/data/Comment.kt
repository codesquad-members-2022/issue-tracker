package com.example.issu_tracker.data

import androidx.room.Entity
import java.io.Serializable

@Entity
data class Comment(
    val user: User? = null,
    val content: String = "",
    val time: String = ""
) : Serializable