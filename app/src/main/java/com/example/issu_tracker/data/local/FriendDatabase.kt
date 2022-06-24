package com.example.issu_tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.issu_tracker.data.User

@Database(entities = [User::class], version = 1)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}