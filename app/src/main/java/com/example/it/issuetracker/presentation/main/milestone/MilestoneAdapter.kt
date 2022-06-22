package com.example.it.issuetracker.presentation.main.milestone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.MilestoneItemBinding
import com.example.it.issuetracker.domain.model.MileStone

class MilestoneAdapter : ListAdapter<MileStone, MilestoneAdapter.MilestoneViewHolder>(diffUtil) {

    class MilestoneViewHolder(private val binding: MilestoneItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(milestone: MileStone) {
            binding.milestoneInfo = milestone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilestoneViewHolder {
        return MilestoneViewHolder(
            MilestoneItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MilestoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<MileStone>() {
    override fun areItemsTheSame(oldItem: MileStone, newItem: MileStone): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MileStone, newItem: MileStone): Boolean {
        return oldItem.id == newItem.id
    }
}