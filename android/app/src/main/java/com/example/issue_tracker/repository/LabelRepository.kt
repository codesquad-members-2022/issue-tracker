package com.example.issue_tracker.repository

import com.example.issue_tracker.model.Label
import kotlinx.coroutines.flow.Flow

interface LabelRepository {

    fun addLabelList(labelList: Label)

    suspend fun getLabelList(): List<Label>
}