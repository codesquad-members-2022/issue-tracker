package com.example.it.issuetracker.di

import org.koin.dsl.module

val networkModule = module {
    single { provideMoshi() }
    single { provideMoshiConverterFactory(get()) }
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideGithubRetrofit(get(), get()) }
    single { provideGithubService(get()) }
}