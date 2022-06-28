package com.example.it.issuetracker.presentation.main.issue.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.ItemCommentBinding
import com.example.it.issuetracker.domain.model.Comment

class CommentAdapter(
    private val onClickMenu: (View, Long) -> Unit,
) : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(CommentDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding, onClickMenu)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CommentViewHolder(
        private val binding: ItemCommentBinding,
        private val onClickMenu: (View, Long) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment = comment
            binding.layoutLike.isVisible = comment.like != 0
            binding.layoutHate.isVisible = comment.hate != 0
            binding.layoutBest.isVisible = comment.best != 0
            binding.layoutOk.isVisible = comment.ok != 0
            binding.btnOption.setOnClickListener { onClickMenu(it, comment.uid) }
        }
    }

}

class CommentDiffUtil() : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }

}