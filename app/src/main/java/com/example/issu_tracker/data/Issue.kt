package com.example.issu_tracker.data

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.Exception

data class IssueDto(
    var id: String = "",
    val comments: List<Comment> = listOf(),
    val description: String = "",
    val label: List<Label> = listOf(),
    val mileStoneID: String = "",
    val state: Boolean = true,
    val title: String = "",
    val user: User? = null
)

@Entity
data class Issue(
    @ColumnInfo(name = "issue_id")
    var id: String,
    val comments: List<Comment>,
    val description: String,
    val label: List<Label>,
    val mileStone: String,
    val state: Boolean,
    val title: String,
    @Embedded
    val user: User
) : Serializable{
    @PrimaryKey(autoGenerate = true)
    var idx: Int = 0
}

class Converters {
    @TypeConverter
    fun fromCommentList(comments: List<Comment>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.toJson(comments, type)
    }

    @TypeConverter
    fun toCommentList(value: String?): List<Comment> {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromLabelList(comments: List<Label>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Label>>() {}.type
        return gson.toJson(comments, type)
    }

    @TypeConverter
    fun toLabelList(value: String?): List<Label> {
        val gson = Gson()
        val type = object : TypeToken<List<Label>>() {}.type
        return gson.fromJson(value, type)
    }

}

fun IssueDto.toIssue(): Issue? {
    return try {
        Issue(id, comments, description, label, mileStoneID, state, title, user!!)
    } catch (e: Exception) {
        null
    }
}


