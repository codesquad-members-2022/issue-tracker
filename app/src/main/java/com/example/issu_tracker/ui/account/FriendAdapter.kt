package com.example.issu_tracker.ui.account

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.issu_tracker.R
import com.example.issu_tracker.data.User
import com.example.issu_tracker.databinding.ItemFriendBinding

class FriendAdapter : ListAdapter<User, FriendAdapter.FriendViewHolder>(diffUtil) {
    inner class FriendViewHolder(val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.tvFriendName.text = item.name
            Glide.with(itemView.context)
                .load(item.userPhoto)
                .circleCrop()
                .into(binding.ivFiendPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_friend,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.UID == newItem.UID
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }
}
