package com.example.it.issuetracker.di

import com.example.it.issuetracker.data.network.GithubService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val GITHUB_BASE_URL = "https://github.com/login/oauth/"

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

fun provideGithubRetrofit(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGithubService(retrofit: Retrofit): GithubService {
    return retrofit.create(GithubService::class.java)
}