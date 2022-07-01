package com.team1.issuetracker.data.model

data class Milestone(
    val id: Int,
    val title: String,
    val progress : Int,
    val content: String,
    val date: String,
    val open: Int,
    val closed: Int,
    var isSwiped: Boolean = false,
    var isCheckVisible: Boolean = false,
    var isChecked: Boolean = false,
    var state: Boolean = true
)
