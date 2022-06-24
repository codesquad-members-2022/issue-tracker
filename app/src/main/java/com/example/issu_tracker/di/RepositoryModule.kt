package com.example.issu_tracker.di

import com.example.issu_tracker.data.repository.*
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideHomeRepository(fireStore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImpl(fireStore, Dispatchers.IO)
    }

    @Provides
    fun provideFilterRepository(fireStore: FirebaseFirestore): FilterRepository {
        return FilterRepositoryImpl(fireStore)
    }

    @Provides
    fun provideIssueEditorRepository(fireStore: FirebaseFirestore): IssueEditorRepository {
        return IssueEditorRepositoryImpl(fireStore)
    }

    @Provides
    fun provideFireStoreDB(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}