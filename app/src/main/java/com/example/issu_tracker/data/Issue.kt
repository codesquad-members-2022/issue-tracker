package com.example.issu_tracker.data

import com.google.gson.annotations.SerializedName
import java.lang.Exception

data class Label(val color: String = "", val content: String = "")

data class IssueDto(
    val commets: List<Comment> = listOf(),
    val description: String = "",
    val label: List<Label> = listOf(),
    val mileStone: String = "",
    val state: Boolean = true,
    val title: String = "",
    val user: User? = null
)

data class Issue(
    val comments: List<Comment>,
    val description: String,
    val label: List<Label>,
    val mileStone: String,
    val state: Boolean,
    val title: String,
    val user: User
)

fun IssueDto.toIssue(): Issue? {
    return try {
        Issue(commets, description, label, mileStone, state, title, user!!)
    } catch (e: Exception) {
        null
    }
}