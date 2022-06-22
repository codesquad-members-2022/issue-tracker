package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.datasource.*
import org.koin.dsl.module

val dataSourceModule = module {
    single<IssueTrackerDataSource> { IssueTrackerDefaultDataSource() }
    single<LabelDataSource> { LabelDataSourceDefaultImpl(get()) }
    single<MilestoneDataSource> { MilestoneDataSourceDefaultImpl(get()) }
}