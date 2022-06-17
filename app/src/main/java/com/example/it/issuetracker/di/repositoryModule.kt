package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.repository.IssueTrackerRepositoryImpl
import com.example.it.issuetracker.data.repository.LabelRepositoryImpl
import com.example.it.issuetracker.data.repository.LoginRepositoryImpl
import com.example.it.issuetracker.domain.repository.IssueTrackerRepository
import com.example.it.issuetracker.domain.repository.LabelRepository
import com.example.it.issuetracker.domain.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<LabelRepository> { LabelRepositoryImpl(get()) }
    single<IssueTrackerRepository> { IssueTrackerRepositoryImpl(get()) }
}