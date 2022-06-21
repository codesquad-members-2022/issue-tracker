package com.example.it.issuetracker.presentation.main.issue

import java.io.Serializable

enum class Mode(val viewType: Int) : Serializable {
    DEFAULT(0), EDIT(1)
}