package com.example.it.issuetracker.presentation.main.issue.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.ItemMilestoneBinding
import com.example.it.issuetracker.domain.model.MileStone

class MilestoneAdapter :
    ListAdapter<MileStone, MilestoneAdapter.MileStoneViewHolder>(MileStoneDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MileStoneViewHolder {
        val binding =
            ItemMilestoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MileStoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MileStoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MileStoneViewHolder(private val binding: ItemMilestoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(milestone: MileStone) {
            binding.milestone = milestone
        }
    }
}

class MileStoneDiffUtil : DiffUtil.ItemCallback<MileStone>() {
    override fun areItemsTheSame(oldItem: MileStone, newItem: MileStone): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MileStone, newItem: MileStone): Boolean {
        return oldItem == newItem
    }

}