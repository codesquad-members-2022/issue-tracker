package com.example.issu_tracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(val UID: String = "", val name: String = "", val userPhoto: String?=null) :
    Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class UserDto(val UID: String = "", val name: String = "", val userPhoto: String)
