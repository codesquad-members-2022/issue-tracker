package com.example.issue_tracker.model

data class Issue(
    val issueId: Int,
    val mileStone: String,
    val title: String,
    val contents: String,
    val label: Label,
    var isSwiped: Boolean = false,
    var isLongClicked: Boolean = false
)

data class Label(
    val labelId: Int?,
    val labelTitle: String?,
    val labelContents: String?,
    val labelColor: String?
)

data class MileStone(
    val mileStoneId: Int?,
    val title: String?,
    val description: String?,
    val dueDate: String?,
    val openedIssueCount: Int = 0,
    val closedIssueCount: Int = 0,
    val progress: String = ""
)
