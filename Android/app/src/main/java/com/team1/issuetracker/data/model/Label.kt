package com.team1.issuetracker.data.model

data class Label(
    val id: Int,
    val color: String,
    val title: String,
    val description: String,
    var isSwiped: Boolean = false,
    var isCheckVisible: Boolean = false,
    var isChecked: Boolean = false,
    var state: Boolean = true
)