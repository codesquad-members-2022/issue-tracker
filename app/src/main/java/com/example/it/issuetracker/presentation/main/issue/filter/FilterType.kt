package com.example.it.issuetracker.presentation.main.issue.filter

enum class FilterType(val type: Int) {
    STATE(0),
    WRITER(1),
    LABEL(2),
    MILESTONE(3)
}