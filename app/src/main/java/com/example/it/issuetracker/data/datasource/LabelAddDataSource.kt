package com.example.it.issuetracker.data.datasource

interface LabelAddDataSource {

    suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String
    )
}