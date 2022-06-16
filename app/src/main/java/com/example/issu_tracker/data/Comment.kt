package com.example.issu_tracker.data

import com.google.firebase.Timestamp

data class Comment(val user: User, val content: String, val date: String)