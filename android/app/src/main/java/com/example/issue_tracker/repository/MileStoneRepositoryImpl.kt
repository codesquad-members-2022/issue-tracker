package com.example.issue_tracker.repository

import com.example.issue_tracker.common.addElement
import com.example.issue_tracker.model.MileStone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MileStoneRepositoryImpl @Inject constructor(): MileStoneRepository {

    private val _mileStoneList = MutableStateFlow<MutableList<MileStone>>(mutableListOf())

    override fun addLabelList(mileStone: MileStone) {
        _mileStoneList.addElement(mileStone)
    }

    override fun getLabelList(): Flow<List<MileStone>> {
        return _mileStoneList
    }
}