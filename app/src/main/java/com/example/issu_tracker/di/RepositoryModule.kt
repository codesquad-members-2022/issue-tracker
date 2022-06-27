package com.example.issu_tracker.di

import android.content.Context
import androidx.room.Room
import com.example.issu_tracker.data.Repository
import com.example.issu_tracker.data.local.FriendDatabase
import com.example.issu_tracker.data.local.UserDao
import com.example.issu_tracker.data.repository.*
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    fun provideHomeRepository(fireStore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImpl(fireStore)
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

    @Provides
    fun provideFriendRemoteRepository(fireStore: FirebaseFirestore): FriendRemoteRepository {
        return FriendRemoteRepositoryIml(fireStore)
    }

    @Singleton
    @Provides
    fun provideUserLocalDataBase(
        @ApplicationContext context: Context
    ): FriendDatabase = Room
        .databaseBuilder(context, FriendDatabase::class.java, "friend.db")
        .build()

    @Singleton
    @Provides
    fun provideUserDao(friendDatabase: FriendDatabase): UserDao = friendDatabase.userDao()

    @Singleton
    @Provides
    fun provideRepository(
        friendDatabase: FriendDatabase,
        friendRemoteDatabase: FriendRemoteRepository
    ): Repository = Repository(
        friendDatabase, friendRemoteDatabase
    )

}