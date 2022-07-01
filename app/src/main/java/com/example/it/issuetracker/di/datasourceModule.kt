package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.datasource.*
import com.example.it.issuetracker.data.datasource.local.IssueTrackerDefaultDataSource
import com.example.it.issuetracker.data.datasource.remote.IssueTrackerRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single<IssueTrackerDataSource>(named("local_data_source")) { IssueTrackerDefaultDataSource() }
    single<IssueTrackerDataSource>(named("remote_data_source")) { IssueTrackerRemoteDataSource(get()) }
    single<LabelDataSource> { LabelDataSourceDefaultImpl(get()) }
    single<MilestoneDataSource> { MilestoneDataSourceDefaultImpl(get()) }
}