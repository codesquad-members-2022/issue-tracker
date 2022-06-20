package com.example.issu_tracker

import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.ui.issue.IssueAdapter
import java.lang.Math.max

class SwipeHelperCallback : ItemTouchHelper.Callback() {

    private var clamp = 0f
    private var currentDx = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        val view = getView(viewHolder)
        clamp = view.width.toFloat() / 10 * 3

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
        (viewHolder as IssueAdapter.IssueViewHolder).isSwiped = currentDx <= -clamp

        // 스와이프 위치적으로 없어지는 기준 설정
        return 2f
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        //super.clearView(recyclerView, viewHolder)

        getDefaultUIUtil().clearView(getView(viewHolder))
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        //super.onSelectedChanged(viewHolder, actionState)
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
        Log.d("eventEvent", "drag")
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)
            val isSwiped = (viewHolder as IssueAdapter.IssueViewHolder).isSwiped
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
        val maxSwipedPosition = -view.width.toFloat() / 10 * 3
        val right = 0f

        val x = if (isSwiped) {
            if (currentlyActive) dX - clamp else -clamp
        } else dX
        return kotlin.math.min(max(maxSwipedPosition, x), right)
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as IssueAdapter.IssueViewHolder).itemView.findViewById(R.id.cl_swipe_container) // 아이템뷰에서 스와이프 영역에 해당하는 뷰 가져오기
    }
}