package com.example.it.issuetracker.domain.repository

interface LabelAddRepository {

    suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String
    )
}