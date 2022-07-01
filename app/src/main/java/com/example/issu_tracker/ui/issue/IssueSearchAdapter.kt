package com.example.issu_tracker.ui.issue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.databinding.ItemIssueBinding

class IssueSearchAdapter : ListAdapter<IssueList, IssueSearchAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(issue: IssueList) {
            if (issue is IssueList.Issue) {
                binding.issue = issue
            }
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<IssueList>() {
            override fun areItemsTheSame(oldItem: IssueList, newItem: IssueList): Boolean {
                return if (oldItem is IssueList.Issue && newItem is IssueList.Issue) {
                    oldItem.title == newItem.title
                } else false
            }

            override fun areContentsTheSame(oldItem: IssueList, newItem: IssueList): Boolean {
                return oldItem == newItem
            }

        }
    }

}