package com.example.issu_tracker.data

data class NewIssue(
    var id: String,
    val comments: List<Comment>,
    val description: String,
    val label: List<Label>,
    val mileStone: String,
    val state: Boolean,
    val title: String,
    val user: User
)
