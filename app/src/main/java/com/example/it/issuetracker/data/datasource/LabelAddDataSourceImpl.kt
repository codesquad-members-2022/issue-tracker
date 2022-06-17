package com.example.it.issuetracker.data.datasource

import com.example.it.issuetracker.data.dto.LabelInfo
import com.example.it.issuetracker.data.network.IssueTrackerService

class LabelAddDataSourceImpl(
    private val issueTrackerApi: IssueTrackerService
) : LabelAddDataSource {

    override suspend fun addLabel(
        title: String,
        description: String,
        backgroundColor: String,
        textColor: String
    ) {
        LabelFakeDatabase.add(
            LabelInfo(LabelFakeDatabase.size + 1, title, description, backgroundColor, textColor)
        )
    }
}