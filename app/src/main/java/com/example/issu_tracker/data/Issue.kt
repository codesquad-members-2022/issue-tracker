package com.example.issu_tracker.data
import java.lang.Exception

data class IssueDto(
    var id: String = "",
    val commets: List<Comment> = listOf(),
    val description: String = "",
    val label: List<Label> = listOf(),
    val mileStone: String = "",
    val state: Boolean = true,
    val title: String = "",
    val user: User? = null
)

data class Issue(
    var id: String,
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
        Issue(id, commets, description, label, mileStone, state, title, user!!)
    } catch (e: Exception) {
        null
    }
}


