package com.example.issu_tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.issu_tracker.data.Comment
import com.example.issu_tracker.data.Converters
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.data.User

@Database(entities = [User::class , Issue::class ], version = 1)
@TypeConverters(Converters::class)
abstract class IssueTrackerDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun issueDao(): IssueDao
}