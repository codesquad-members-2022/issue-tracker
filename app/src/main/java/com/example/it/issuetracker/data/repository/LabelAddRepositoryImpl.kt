package com.example.it.issuetracker.data.repository

import com.example.it.issuetracker.data.datasource.LabelAddDataSource
import com.example.it.issuetracker.domain.repository.LabelAddRepository

class LabelAddRepositoryImpl(
    private val labelAddDataSource: LabelAddDataSource
) : LabelAddRepository {

    override suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String
    ) {
        labelAddDataSource.addLabel(title, description, backgroundColor, textColor)
    }
}