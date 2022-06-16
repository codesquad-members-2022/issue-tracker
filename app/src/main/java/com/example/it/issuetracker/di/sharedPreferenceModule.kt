package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.repository.SharedPrefDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefModule = module {
    single { SharedPrefDataSource(androidContext()) }
}