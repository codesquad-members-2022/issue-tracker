package com.example.it.issuetracker.domain.model

import com.example.it.issuetracker.presentation.main.issue.list.Mode
import com.example.it.issuetracker.presentation.main.issue.list.Mode.DEFAULT
import java.io.Serializable

data class Issue(
    val id: Long,
    val title: String,
    val description: String,
    val label: List<Label>,
    var state: Boolean,
    val mileStone: String,
    var isChecked: Boolean = false,
    var viewType: Mode = DEFAULT,
    var isSwiped: Boolean = false,
) : Serializable