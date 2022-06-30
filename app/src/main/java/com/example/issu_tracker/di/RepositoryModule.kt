package com.example.issu_tracker.di

import android.content.Context
import androidx.room.Room
import com.example.issu_tracker.data.Converters
import com.example.issu_tracker.data.IssueDto
import com.example.issu_tracker.data.local.IssueDao
import com.example.issu_tracker.data.repository.Repository
import com.example.issu_tracker.data.local.IssueTrackerDatabase
import com.example.issu_tracker.data.local.UserDao
import com.example.issu_tracker.data.repository.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestoreSettings
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
        val setting = firestoreSettings { isPersistenceEnabled = false }
        val db = FirebaseFirestore.getInstance()
        db.firestoreSettings = setting
        return db
    }

    @Provides
    fun provideFriendRemoteRepository(fireStore: FirebaseFirestore): FriendRemoteRepository {
        return FriendRemoteRepositoryIml(fireStore)
    }

    @Singleton
    @Provides
    fun provideUserLocalDataBase(
        @ApplicationContext context: Context
    ): IssueTrackerDatabase = Room
        .databaseBuilder(context, IssueTrackerDatabase::class.java, "friend.db")
        .addTypeConverter(Converters())
        .build()

    @Singleton
    @Provides
    fun provideUserDao(friendDatabase: IssueTrackerDatabase): UserDao = friendDatabase.userDao()

    @Singleton
    @Provides
    fun provideIssueDao(friendDatabase: IssueTrackerDatabase): IssueDao = friendDatabase.issueDao()

    @Singleton
    @Provides
    fun provideRepository(
        friendDatabase: IssueTrackerDatabase,
        friendRemoteDatabase: FriendRemoteRepository
    ): Repository = Repository(
        friendDatabase, friendRemoteDatabase
    )

}