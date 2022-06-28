package com.example.issu_tracker.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Comment
import com.example.issu_tracker.databinding.ItemCommentBinding
import com.example.issu_tracker.ui.home.userUid

class CommentAdapter() : ListAdapter<Comment, RecyclerView.ViewHolder>(diffUtil) {
    // 두 코멘트의 확장성을 고려해
    inner class MyCommentViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment = comment
        }
    }

    inner class OtherCommentViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment = comment
            binding.btnEditComment.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MY_COMMENT -> MyCommentViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_comment,
                    parent,
                    false
                )
            )
            else -> OtherCommentViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_comment,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).user?.UID) {
            userUid -> MY_COMMENT
            else -> OTHER_COMMENT
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyCommentViewHolder -> {
                holder.bind(getItem(position))
            }
            is OtherCommentViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    companion object {

        const val MY_COMMENT = 0
        const val OTHER_COMMENT = 1

        val diffUtil = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.content == newItem.content
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }

        }
    }


}