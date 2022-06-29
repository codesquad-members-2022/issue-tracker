package com.example.issu_tracker.ui.issue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.databinding.ItemIssueBinding

class IssueSearchAdapter : ListAdapter<Issue, IssueSearchAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(issue: Issue) {
            binding.issue = issue
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Issue>() {
            override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
                return oldItem == newItem
            }

        }
    }

}