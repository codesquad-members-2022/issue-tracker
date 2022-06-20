package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.datasource.UserSharedPrefDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefModule = module {
    single { UserSharedPrefDataSource(androidContext()) }
}