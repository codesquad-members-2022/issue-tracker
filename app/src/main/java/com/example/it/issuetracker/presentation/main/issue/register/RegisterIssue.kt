package com.example.it.issuetracker.presentation.main.issue.register

data class RegisterIssue(
    val title: String,
    val description: String,
    val labels: List<String> = emptyList(),
    val milestones: String = "",
    val assignee: String = ""
)
