package com.example.it.issuetracker.presentation.main.issue

import com.example.it.issuetracker.domain.model.Issue
import java.util.*

data class CacheIssue(val issues: SortedMap<Int, Issue>, val message: String)
