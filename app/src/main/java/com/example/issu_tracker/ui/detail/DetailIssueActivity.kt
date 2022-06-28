package com.example.issu_tracker.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.databinding.ActivityDetailIssueBinding
import com.example.issu_tracker.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailIssueActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailIssueBinding

    lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_issue)

        val issue = intent.extras?.get("issue") as Issue
        binding.tvIssueTitle.text = issue.title

        adapter = CommentAdapter()
        binding.rvCommentList.adapter = adapter
        adapter.submitList(issue.comments)
        binding.rvCommentList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        Log.d("testtest" , adapter.itemCount.toString())

    }
}