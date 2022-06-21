package com.example.it.issuetracker.domain.model

import com.example.it.issuetracker.presentation.main.issue.Mode
import com.example.it.issuetracker.presentation.main.issue.Mode.DEFAULT
import java.io.Serializable

data class Issue(
    val id: Long,
    val title: String,
    val description: String,
    val label: List<Label>,
    var state: Boolean,
    val mileStone: MileStone,
    var isChecked: Boolean = false,
    var viewType: Mode = DEFAULT,
) : Serializable