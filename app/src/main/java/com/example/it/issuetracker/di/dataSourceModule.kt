package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.datasource.IssueTrackerDataSource
import com.example.it.issuetracker.data.datasource.IssueTrackerDefaultDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IssueTrackerDataSource> { IssueTrackerDefaultDataSource() }
}