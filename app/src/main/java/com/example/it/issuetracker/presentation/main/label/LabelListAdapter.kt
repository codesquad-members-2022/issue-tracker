package com.example.it.issuetracker.presentation.main.label

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.data.dto.LabelInfo
import com.example.it.issuetracker.databinding.LabelItemBinding

class LabelListAdapter : ListAdapter<LabelInfo, LabelListAdapter.LabelViewHolder>(diffUtil) {

    class LabelViewHolder(private val binding: LabelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(labelInfo: LabelInfo) {
            binding.labelInfo = labelInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        return LabelViewHolder(
            LabelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LabelInfo>() {
            override fun areItemsTheSame(oldItem: LabelInfo, newItem: LabelInfo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LabelInfo, newItem: LabelInfo): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}