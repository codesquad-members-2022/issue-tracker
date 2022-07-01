package com.example.issu_tracker.data

import java.io.Serializable
import java.lang.Exception

data class IssueDto(
    var id: String = "",
    val comments: List<Comment> = listOf(),
    val description: String = "",
    val label: List<Label> = listOf(),
    val mileStoneID: String = "",
    val state: Boolean = true,
    val title: String = "",
    val user: User? = null
)

sealed class IssueList : Serializable {

    object IssueProgressBar : IssueList()

    data class Issue(
        var id: String,
        val comments: List<Comment>,
        val description: String,
        val label: List<Label>,
        val mileStone: String,
        val state: Boolean,
        val title: String,
        val user: User
    ) : Serializable, IssueList()
}



fun IssueDto.toIssue(): IssueList.Issue? {
    return try {
        IssueList.Issue(id, comments, description, label, mileStoneID, state, title, user!!)
    } catch (e: Exception) {
        null
    }
}


