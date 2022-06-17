package com.example.issu_tracker.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.repository.HomeRepository
import com.example.issu_tracker.data.repository.HomeRepositoryImpl
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var repository: HomeRepository
    private lateinit var viewModel: HomeViewModel

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = HomeRepositoryImpl()
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun getIssueList_IssueList() {
        // Given

        // When

        // Then
        viewModel.getIssueList()
        assertThat(viewModel.issueList.value, `is`(listOf(
            Issue(0, "마일스톤", "Document", "그라운드 룰", "Issue_tracker 의 그라운드 룰을 정의합니다."),
            Issue(1, "마일스톤", "Feature", "리사이클러뷰 구현", "리사이클러뷰를 구현했습니다."),
        )) )
    }
}