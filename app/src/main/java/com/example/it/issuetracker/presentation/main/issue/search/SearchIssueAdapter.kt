package com.example.it.issuetracker.presentation.main.issue.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.ItemIssueBinding
import com.example.it.issuetracker.domain.model.Issue
import com.example.it.issuetracker.presentation.main.issue.list.IssueDiffUtil
import com.example.it.issuetracker.presentation.main.issue.list.LabelAdapter

class SearchIssueAdapter : ListAdapter<Issue, SearchIssueAdapter.IssueViewHolder>(IssueDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IssueViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = LabelAdapter()

        fun bind(issue: Issue) {
            binding.issue = issue
            binding.rvLabel.adapter = adapter
            adapter.submitList(issue.label)
        }
    }
}