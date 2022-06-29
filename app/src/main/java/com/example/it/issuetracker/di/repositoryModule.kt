package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.repository.IssueTrackerRepositoryImpl
import com.example.it.issuetracker.data.repository.LabelRepositoryImpl
import com.example.it.issuetracker.data.repository.LoginRepositoryImpl
import com.example.it.issuetracker.data.repository.MilestoneRepositoryImpl
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.domain.repository.LoginRepository
import com.example.it.issuetracker.domain.repository.MilestoneRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<LabelRepository> { LabelRepositoryImpl(get()) }
    single<IssueTrackerRepository> {
        IssueTrackerRepositoryImpl(
            get(named("local_data_source")),
            get(named("remote_data_source"))
        )
    }
    single<MilestoneRepository> { MilestoneRepositoryImpl(get()) }
}