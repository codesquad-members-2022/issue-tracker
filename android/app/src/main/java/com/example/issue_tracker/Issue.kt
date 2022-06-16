package com.example.issue_tracker

data class Issue(
    val issueId: Int,
    val mileStone: String,
    val title: String,
    val contents: String,
    val label: Label
)

data class Label(
    val labelId: Int,
    val labelContents: String,
    val labelColor: String
)
