package com.example.issu_tracker.ui.issue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.R
import com.example.issu_tracker.data.IssueList
import com.example.issu_tracker.databinding.ItemIssueBinding
import com.example.issu_tracker.databinding.ItemLoadingBinding


class IssueAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val issueListItems = mutableListOf<IssueList>()
    var issueAdapterEventListener: IssueAdapterEventListener? = null
    var isEditMode = false
    val swipedIssueList = mutableListOf<IssueList.Issue>()
    var rootWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ISSUE -> {
                val binding: ItemIssueBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_issue,
                    parent,
                    false
                )
                IssueViewHolder(binding)
            }
            else -> {
                val binding =
                    ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                IssueLoadViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is IssueAdapter.IssueViewHolder) {
            holder.bind(issueListItems[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (issueListItems[position]) {
            is IssueList.Issue -> VIEW_TYPE_ISSUE
            else -> VIEW_TYPE_ISSUE_PROGRESS_BAR
        }
    }

    inner class IssueViewHolder(private val binding: ItemIssueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var issue: IssueList? = null
        fun addSwipedIssue(issue: IssueList.Issue) = swipedIssueList.add(issue)
        fun deleteSwipedIssue(issue: IssueList.Issue) = swipedIssueList.remove(issue)
        fun getSwipedIssueList(): List<IssueList> {
            return swipedIssueList
        }

        fun bind(issue: IssueList) {
            if (issue is IssueList.Issue) {
                this.issue = issue
                binding.issue = issue

                if (rootWidth == 0) {
                    getRootWidth()
                }

                // 스와이프 상태 유지 코드
                if (swipedIssueList.contains(issue)) {
                    binding.clSwipeContainer.x =
                        (rootWidth) * (-1f / 10 * 3)
                } else binding.clSwipeContainer.translationX = 0f

                // 스와이프 후 클릭시 닫기
                binding.tvDeleteClose.setOnClickListener {
                    if (swipedIssueList.contains(issue)) {
                        println("이건 됨")
                        issueAdapterEventListener?.updateIssueState(issue.id, false)
                        swipedIssueList.remove(issue)
                    }
                }

                // EditMode 에서의 상단 변화 코드
                if (isEditMode) {
                    binding.cbIssueSelector.visibility = View.VISIBLE
                    binding.cbIssueSelector.isChecked = false
                } else {
                    binding.cbIssueSelector.visibility = View.GONE
                }

                binding.root.setOnLongClickListener {
                    issueAdapterEventListener?.switchToEditMode(issue.id)
                    return@setOnLongClickListener (true)
                }


                binding.root.setOnClickListener {
                    issueAdapterEventListener?.getIntoDetail(issue)
                }

                // EditMode 에서의 셀렉팅
                binding.cbIssueSelector.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        issueAdapterEventListener?.addInCheckList(issue)
                    } else issueAdapterEventListener?.deleteInCheckList(issue)
                }
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

    class IssueLoadViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return issueListItems.size
    }

    fun submitList(issueList: List<IssueList>) {
        issueListItems.clear()
        issueListItems.addAll(issueList)
    }

    companion object {
        const val VIEW_TYPE_ISSUE = 0
        const val VIEW_TYPE_ISSUE_PROGRESS_BAR = 1
    }
}