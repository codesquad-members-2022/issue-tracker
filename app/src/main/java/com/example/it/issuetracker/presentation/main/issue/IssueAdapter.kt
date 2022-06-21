package com.example.it.issuetracker.presentation.main.issue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.ItemEditIssueBinding
import com.example.it.issuetracker.databinding.ItemIssueBinding
import com.example.it.issuetracker.domain.model.Issue

class IssueAdapter(
    private val toggle: () -> Unit,
) : ListAdapter<Issue, RecyclerView.ViewHolder>(IssueDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding =
                    ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueViewHolder(binding, toggle)
            }
            else -> {
                val binding =
                    ItemEditIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueEditViewHolder(binding, toggle)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IssueViewHolder -> {
                holder.bind(getItem(position))
            }
            is IssueEditViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].viewType.viewType
    }

    class IssueViewHolder(
        private val binding: ItemIssueBinding,
        private val toggle: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = LabelAdapter()

        fun bind(issue: Issue) {
            binding.issue = issue
            binding.rvLabel.adapter = adapter
            adapter.submitList(issue.label)
            binding.container.setOnLongClickListener {
                toggle()
                true
            }
        }
    }

    class IssueEditViewHolder(
        private val binding: ItemEditIssueBinding,
        private val toggle: () -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = LabelAdapter()

        fun bind(issue: Issue) {
            binding.issue = issue
            binding.rvLabel.adapter = adapter
            adapter.submitList(issue.label)
            binding.cbEdit.setOnCheckedChangeListener { _, isChecked ->
                issue.isChecked = isChecked
            }
            binding.container.setOnLongClickListener {
                toggle()
                true
            }
        }
    }
}

class IssueDiffUtil : DiffUtil.ItemCallback<Issue>() {
    override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem == newItem
    }

}