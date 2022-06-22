package com.example.it.issuetracker.di

import com.example.it.issuetracker.presentation.login.LoginViewModel
import com.example.it.issuetracker.presentation.main.issue.list.IssueViewModel
import com.example.it.issuetracker.presentation.main.issue.filter.FilterViewModel
import com.example.it.issuetracker.presentation.main.label.LabelAddViewModel
import com.example.it.issuetracker.presentation.main.label.LabelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { LabelViewModel(get()) }
    viewModel { LabelAddViewModel(get()) }
    viewModel { IssueViewModel(get()) }
    viewModel { FilterViewModel(get(), get(), get()) }
}