package com.example.it.issuetracker.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { provideMoshi() }
    single { provideMoshiConverterFactory(get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single(named("issue_tracker")) { provideIssueTrackerRetrofit(get(), get()) }
    single { provideIssueTrackerService(get(named("issue_tracker"))) }

}