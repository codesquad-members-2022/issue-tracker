package com.example.issu_tracker.ui.issue

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.databinding.ItemIssueBinding

class IssueAdapter : ListAdapter<Issue, IssueAdapter.IssueViewHolder>(diffUtil) {
    var swipeDeleteListener: SwipeDeleteListener? = null

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

    inner class IssueViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isSwiped = false

        fun bind(issue: Issue) {
            binding.issue = issue
            Log.d("eventEvent", isSwiped.toString())
            binding.tvDeleteClose.setOnClickListener {
                if (isSwiped) {
                    swipeDeleteListener?.deleteItem(issue.id)
                }
            }

            binding.root.setOnLongClickListener {
                Log.d("eventEvent", "LongClick")
                return@setOnLongClickListener (true)
            }
            binding.root.setOnClickListener {
                Log.d("eventEvent", "click")
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
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