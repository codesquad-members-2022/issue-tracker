package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.datasource.LabelAddDataSource
import com.example.it.issuetracker.data.datasource.LabelAddDataSourceImpl
import com.example.it.issuetracker.data.datasource.LabelDataSource
import com.example.it.issuetracker.data.datasource.LabelDataSourceImpl
import org.koin.dsl.module

val datasourceModule = module {

    single<LabelDataSource> { LabelDataSourceImpl(get()) }
    single<LabelAddDataSource> { LabelAddDataSourceImpl(get()) }
}