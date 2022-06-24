package com.example.issu_tracker.ui.common

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.R
import com.example.issu_tracker.ui.issue.IssueAdapter
import java.lang.Math.max

class SwipeHelperCallback() : ItemTouchHelper.Callback() {

    private var clamp = 0f
    private var currentDx = 0f


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        val view = getView(viewHolder)
        clamp = view.width.toFloat() * (0.3f)
        return makeMovementFlags(0, LEFT or RIGHT)
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        // 스와이프 속도에 따라 없어지는 기준 설정
        return defaultValue * 100
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        // 현재 위치가 clamp 이상 갔을 때 스와이프 상태로 판단한다.
        // 스와이프 위치적으로 없어지는 기준 설정
        (viewHolder as IssueAdapter.IssueViewHolder).issue?.let {
            if (currentDx <= -clamp) {
                viewHolder.addSwipedIssue(it)
            } else {
                viewHolder.deleteSwipedIssue(it)
            }
        }
        return 2f
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        getDefaultUIUtil().clearView(getView(viewHolder))
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            getDefaultUIUtil().onSelected(getView(it))
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)
            var isSwiped = (viewHolder as IssueAdapter.IssueViewHolder).getSwipedIssueList()
                .contains(viewHolder.issue)
            val x = getClampHorizontalPosition(view, dX, isSwiped, isCurrentlyActive)

            currentDx = x
            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                x,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    private fun getClampHorizontalPosition(
        view: View,
        dX: Float,
        isSwiped: Boolean,
        currentlyActive: Boolean
    ): Float {

        val right = 0f

        val x = if (isSwiped) {
            if (currentlyActive) -clamp + dX else -clamp
        } else dX
        return kotlin.math.min(max(-clamp, x), right)
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as IssueAdapter.IssueViewHolder).itemView.findViewById(R.id.cl_swipe_container) // 아이템뷰에서 스와이프 영역에 해당하는 뷰 가져오기
    }
}