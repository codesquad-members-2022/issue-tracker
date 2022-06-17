package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.datasource.IssueTrackerDefaultDataSource
import com.example.it.issuetracker.data.datasource.LabelDataSource
import com.example.it.issuetracker.data.datasource.LabelDataSourceDefaultImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<IssueTrackerDataSource> { IssueTrackerDefaultDataSource() }
    single<LabelDataSource> { LabelDataSourceDefaultImpl(get()) }
}