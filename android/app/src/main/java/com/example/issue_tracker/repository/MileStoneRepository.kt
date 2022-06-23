package com.example.issue_tracker.repository

import com.example.issue_tracker.model.MileStone
import kotlinx.coroutines.flow.Flow

interface MileStoneRepository {

    fun addLabelList(mileStone: MileStone)

    fun getLabelList(): Flow<List<MileStone>>

}