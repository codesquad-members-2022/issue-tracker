package com.example.it.issuetracker.presentation.main.issue.register

import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.domain.model.Member
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.presentation.common.Constants.FILTER_RESET_NUMBER
import com.example.it.issuetracker.presentation.common.Constants.INIT_ERROR_MSG_ID

data class RegisterIssueUiState(
    val title: String = "",
    val description: String = "",
    val filterItems: FilterItems = FilterItems(),
    val selectedFilters: SelectedFilters = SelectedFilters(),
    val requiredField: RequireField = RequireField(),
    val errorMsgId: Int = INIT_ERROR_MSG_ID,
    val dataLoading: Boolean = false,
) {
    data class FilterItems(
        val labelList: List<Label> = emptyList(),
        val milestoneList: List<MileStone> = emptyList(),
        val assigneeList: List<Member> = emptyList(),
    )

    data class SelectedFilters(
        val labelIndex: List<Int>? = emptyList(),
        val milestoneIndex: Int? = FILTER_RESET_NUMBER,
        val assigneeIndex: Int? = FILTER_RESET_NUMBER,
    )

    data class RequireField(
        val isEmptySubject: Boolean = true,
        val isEmptyDescription: Boolean = true,
    )
}