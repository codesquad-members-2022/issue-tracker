package com.example.issu_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.databinding.ItemIssueBinding

class IssueAdapter : ListAdapter<Issue, IssueAdapter.IssueViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding: ItemIssueBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_issue,
            parent,
            false
        )
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IssueViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(issue: Issue) {
            binding.issue = issue
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Issue>() {
            override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
                return oldItem.index == newItem.index
            }

            override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
                return oldItem == newItem
            }

        }
    }


}