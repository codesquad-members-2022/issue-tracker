package com.example.issue_tracker

import java.io.Serializable

data class Issue(
    val issueId: Int,
    val mileStone: String,
    val title: String,
    val contents: String,
    val label: Label
)

data class Label(
    val labelId: Int?,
    val labelTitle: String?,
    val labelContents: String?,
    val labelColor: String?
): Serializable
