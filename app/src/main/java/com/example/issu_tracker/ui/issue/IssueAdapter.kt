package com.example.issu_tracker.ui.issue

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Issue
import com.example.issu_tracker.databinding.ItemIssueBinding


class IssueAdapter() : ListAdapter<Issue, IssueAdapter.IssueViewHolder>(diffUtil) {
    var issueAdapterEventListener: IssueAdapterEventListener? = null
    var isEditMode = false
    val swipedIssueList = mutableListOf<Issue>()
    var rootWidth = 0

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
        var issue: Issue? = null
        fun addSwipedIssue(issue: Issue) = swipedIssueList.add(issue)
        fun deleteSwipedIssue(issue: Issue) = swipedIssueList.remove(issue)
        fun getSwipedIssueList(): List<Issue> {
            return swipedIssueList
        }

        fun bind(issue: Issue) {
            this.issue = issue
            binding.issue = issue

            if (rootWidth == 0) {
                getRootWidth()
            }

            if (swipedIssueList.contains(issue)) {
                binding.clSwipeContainer.x =
                    (rootWidth) * (-1f / 10 * 3)
            } else binding.clSwipeContainer.translationX = 0f

            if (isEditMode) {
                binding.cbIssueSelector.visibility = View.VISIBLE
                binding.cbIssueSelector.isChecked = false

            } else {
                binding.cbIssueSelector.visibility = View.GONE
            }

            binding.tvDeleteClose.setOnClickListener {
                if (swipedIssueList.contains(issue)) {
                    issueAdapterEventListener?.updateIssueState(issue.id, false)
                    swipedIssueList.remove(issue)
                }
            }

            binding.root.setOnLongClickListener {
                issueAdapterEventListener?.switchToEditMode(issue.id)
                return@setOnLongClickListener (true)
            }

            binding.root.setOnClickListener {
                issueAdapterEventListener?.getIntoDetail(issue)
            }

            binding.cbIssueSelector.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    issueAdapterEventListener?.addInCheckList(issue)
                } else issueAdapterEventListener?.deleteInCheckList(issue)
            }


        }

        private fun getRootWidth() {
            binding.root.viewTreeObserver.addOnGlobalLayoutListener(object :
                OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    //뷰의 생성된 후 크기와 위치 구하기
                    rootWidth = binding.root.width
                    binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)

                }
            })
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