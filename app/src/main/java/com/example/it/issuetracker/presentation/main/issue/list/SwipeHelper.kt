package com.example.it.issuetracker.presentation.main.issue.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.R
import com.example.it.issuetracker.presentation.common.BaseSwipeHelper

class SwipeHelper : BaseSwipeHelper() {
    override fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as IssueAdapter.IssueViewHolder).itemView.findViewById(R.id.container)
    }

    override fun setClamped(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
        viewHolder as IssueAdapter.IssueViewHolder
        viewHolder.setClamped(isClamped)
    }

    override fun getClamped(viewHolder: RecyclerView.ViewHolder): Boolean {
        viewHolder as IssueAdapter.IssueViewHolder
        return viewHolder.getClamped()
    }

}
