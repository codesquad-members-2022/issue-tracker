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

    fun getData(key: String): String {
        return requireNotNull(sharedPref.getString(key, ""))
    }

    companion object {
        const val NULL_POINT_ERROR_MESSAGE = "jwt의 값을 가지고 있지 않습니다."
    }
}