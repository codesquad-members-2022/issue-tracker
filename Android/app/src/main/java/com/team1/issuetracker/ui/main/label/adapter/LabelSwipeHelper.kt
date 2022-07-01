package com.team1.issuetracker.ui.main.label.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.team1.issuetracker.R
import com.team1.issuetracker.common.SwipeHelper
import com.team1.issuetracker.ui.main.label.adapter.LabelAdapter
import com.team1.issuetracker.ui.main.milestone.adapter.MilestoneAdapter

class LabelSwipeHelper: SwipeHelper() {

    override fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as LabelAdapter.LabelViewHolder).itemView.findViewById(R.id.milestone_view) // 아이템뷰에서 스와이프 영역에 해당하는 뷰 가져오기
    }

    override fun setClamped(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean){
        viewHolder as LabelAdapter.LabelViewHolder
        viewHolder.setClamped(isClamped)
    }

    override fun getClamped(viewHolder: RecyclerView.ViewHolder): Boolean{
        viewHolder as LabelAdapter.LabelViewHolder

        return viewHolder.getClamped()
    }
}