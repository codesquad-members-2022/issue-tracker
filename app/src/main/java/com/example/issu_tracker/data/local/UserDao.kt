package com.example.issu_tracker.data.local


import androidx.room.*
import com.example.issu_tracker.data.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM User") // 테이블의 모든 값을 가져와라
    suspend fun getAll(): List<User>

    @Query("DELETE FROM User WHERE name = :name")
    suspend fun deleteUserByName(name: String)

    @Query("UPDATE User SET userPhoto =:userPhoto,name=:name WHERE UID = :UID")
    suspend fun updateUser(userPhoto: String, UID: String, name: String)

    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()
}