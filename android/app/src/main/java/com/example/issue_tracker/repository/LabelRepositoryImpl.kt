package com.example.issue_tracker.repository

import com.example.issue_tracker.common.addElement
import com.example.issue_tracker.model.Label
import com.example.issue_tracker.model.toLabelList
import com.example.issue_tracker.network.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class LabelRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : LabelRepository {

    private val _labelList = MutableStateFlow<MutableList<Label>>(mutableListOf())

    override fun addLabelList(labelList: Label) {
        _labelList.addElement(labelList)
    }

    override suspend fun getLabelList(): List<Label> =
         apiService.getLabels().labels.toLabelList()


}