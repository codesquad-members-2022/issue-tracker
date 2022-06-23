package com.example.issue_tracker.ui.issue

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issue_tracker.databinding.ItemIssueRecyclerViewBinding
import com.example.issue_tracker.model.Issue

class IssueAdapter(
    private val viewModel: IssueViewModel
) : ListAdapter<Issue, IssueAdapter.IssueViewHolder>(
    IssueDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding =
            ItemIssueRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class IssueViewHolder(private val binding: ItemIssueRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(issue: Issue) {
            binding.issue = issue
            binding.tvCloseIssue.setOnClickListener {
                // TODO 닫기 버튼 클릭 시 로직 구현
                Log.d("테스트", "닫기 클릭")
            }

            when (issue.isLongClicked) {
                true -> binding.issueCheckBox.visibility = View.VISIBLE
                else -> binding.issueCheckBox.visibility = View.GONE
            }

            binding.cvSwipeView.setOnLongClickListener {
                viewModel.changeClickedState()
                notifyDataSetChanged()
                false
            }
        }
    }
}

object IssueDiffCallback : DiffUtil.ItemCallback<Issue>() {
    override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem.issueId == newItem.issueId
    }

    override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem == newItem
    }
}