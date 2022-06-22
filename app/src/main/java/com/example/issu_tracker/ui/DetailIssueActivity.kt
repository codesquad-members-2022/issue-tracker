package com.example.issu_tracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Issue

class DetailIssueActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_issue)
        val issue = intent.getSerializableExtra("issue") as Issue
    }
}