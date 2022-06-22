package com.example.it.issuetracker.domain.model

import java.io.Serializable

data class MileStone(
    val id: Long,
    val title: String,
    val description: String? = null,
    val startDate: String? = null,
    val deadLine: String? = null,
    val openedIssue: Int? = null,
    val closedIssue: Int? = null
) : Serializable