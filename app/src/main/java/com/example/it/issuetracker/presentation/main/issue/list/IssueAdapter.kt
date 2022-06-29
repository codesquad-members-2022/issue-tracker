package com.example.it.issuetracker.presentation.main.issue.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.ItemEditIssueBinding
import com.example.it.issuetracker.databinding.ItemIssueBinding
import com.example.it.issuetracker.domain.model.Issue

class IssueAdapter(
    private val onToggle: () -> Unit,
    private val onClick: (Long) -> Unit,
    private val onClose: (Long) -> Unit,
) : ListAdapter<Issue, RecyclerView.ViewHolder>(IssueDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                val binding =
                    ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueViewHolder(binding, onToggle, onClick, onClose)
            }
            else -> {
                val binding =
                    ItemEditIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueEditViewHolder(binding, onToggle)
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

    inner class IssueViewHolder(
        private val binding: ItemIssueBinding,
        private val onToggle: () -> Unit,
        private val onClick: (Long) -> Unit,
        private val onClose: (Long) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val adapter = LabelAdapter()

        fun bind(issue: Issue) {
            if (issue.isSwiped) binding.container.translationX = binding.root.width * -1f / 10 * 3
            else binding.container.translationX = 0f

            binding.issue = issue
            binding.rvLabel.adapter = adapter
            adapter.submitList(issue.label)
            binding.layoutErase.setOnClickListener {
                Log.d("test", "bind: erase")
                if (getItem(adapterPosition).isSwiped) {
                    onClose.invoke(getItem(adapterPosition).id)
                }
            }
            binding.container.setOnClickListener {
                onClick(issue.id)
            }
            binding.container.setOnLongClickListener {
                onToggle()
                true
            }
        }

        fun setClamped(isClamped: Boolean) {
            getItem(adapterPosition).isSwiped = isClamped
        }

        fun getClamped(): Boolean = getItem(adapterPosition).isSwiped
    }

    class IssueEditViewHolder(
        private val binding: ItemEditIssueBinding,
        private val onToggle: () -> Unit,
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
                onToggle()
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