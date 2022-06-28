package com.example.it.issuetracker.data.datasource

import android.content.Context
import androidx.core.content.edit

class UserSharedPrefDataSource(context: Context) {

    private val sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        sharedPref.edit {
            remove(key)
            putString(key, value)
        }
    }

    fun saveData(key: String, value: Long) {
        sharedPref.edit {
            remove(key)
            putLong(key, value)
        }
    }

    fun getData(key: String): String {
        return requireNotNull(sharedPref.getString(key, ""))
    }
}