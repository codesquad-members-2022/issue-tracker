package com.example.it.issuetracker.di

import com.example.it.issuetracker.config.ISSUE_TRACKER_BASEURL
import com.example.it.issuetracker.data.network.IssueTrackerService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
    MoshiConverterFactory.create(moshi)

fun provideIssueTrackerRetrofit(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ISSUE_TRACKER_BASEURL)
        .addConverterFactory(moshiConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideIssueTrackerService(retrofit: Retrofit): IssueTrackerService {
    return retrofit.create(IssueTrackerService::class.java)
}