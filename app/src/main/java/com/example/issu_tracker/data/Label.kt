package com.example.issu_tracker.data

import androidx.room.Entity
import java.io.Serializable
@Entity
data class Label(val color: String = "", val content: String = "") :Serializable
