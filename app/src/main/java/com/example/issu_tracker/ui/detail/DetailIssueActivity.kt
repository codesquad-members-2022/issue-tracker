package com.example.issu_tracker.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.databinding.ActivityDetailIssueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailIssueActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailIssueBinding
    lateinit var issue: Issue
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_issue)

        issue = intent.extras?.get("issue") as Issue
        binding.tvIssueTitle.text = issue.title

        adapter = CommentAdapter(object : DetailEventListener {
            override fun onClicked() {
                // TODO 자신의 코멘트 더보기 클릭시 구현사항 논의 필요
            }
        })

        setRecyclerview()

        binding.ivDetailInfo.setOnClickListener {
            DetailEditDialogFragment(issue.label.map { it.content }).show(
                supportFragmentManager,
                "DetailInfo"
            )
        }
    }

    private fun setRecyclerview() {
        binding.rvCommentList.adapter = adapter
        adapter.submitList(issue.comments)
        binding.rvCommentList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        supportFragmentManager
    }

}

interface DetailEventListener {
    fun onClicked()
}