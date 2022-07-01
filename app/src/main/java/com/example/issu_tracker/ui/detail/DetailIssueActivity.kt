package com.example.issu_tracker.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.issu_tracker.R
import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.databinding.ActivityDetailIssueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailIssueActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailIssueBinding
    private lateinit var issue: IssueList.Issue
    private lateinit var adapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_issue)

        adapter = CommentAdapter(object : DetailEventListener {
            override fun onClicked() {
                // TODO 자신의 코멘트 더보기 클릭시 구현사항 논의 필요
            }
        })

        issue = intent.extras?.get("issue") as IssueList.Issue
        binding.tvIssueTitle.text = issue.title

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