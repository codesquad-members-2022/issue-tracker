package com.example.it.issuetracker.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val GITHUB_BASE_URL = "https://github.com/login/oauth/"

private const val GITHUB_API_BASE_URL = "https://api.github.com/"

class GithubNetwork {

    companion object {

        private val loggingInterceptor by lazy {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val client by lazy {
            OkHttpClient.Builder().apply {
                addInterceptor(loggingInterceptor)
            }.build()
        }

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private fun createRetrofitBuilder(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
        }

        fun createGithubService(): GithubService {
            return createRetrofitBuilder(GITHUB_BASE_URL)
                .create(GithubService::class.java)
        }

        fun createGithubApiService(): GithubApiService {
            return createRetrofitBuilder(GITHUB_API_BASE_URL)
                .create(GithubApiService::class.java)
        }
    }
}