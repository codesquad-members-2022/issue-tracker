package com.example.issue_tracker.repository

import com.example.issue_tracker.common.setList
import com.example.issue_tracker.model.Label
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class LabelRepositoryImpl @Inject constructor() : LabelRepository {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())

    override fun setLabelList(labelList: Label) {
        _labelList.setList(labelList)
    }

    override fun getLabelList(): Flow<List<Label>> {
        return _labelList
    }
}