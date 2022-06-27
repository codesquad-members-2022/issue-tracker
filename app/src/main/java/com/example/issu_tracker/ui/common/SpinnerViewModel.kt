package com.example.issu_tracker.ui.common

import com.example.issu_tracker.data.ConditionType

interface SpinnerViewModel {
    fun inputSpinnerValue(condition: String, conditionType: Enum<ConditionType>)
}