package com.example.it.issuetracker.presentation.main.issue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.ItemLabelBinding
import com.example.it.issuetracker.domain.model.Label

class LabelAdapter : ListAdapter<Label, LabelAdapter.LabelViewHolder>(LabelDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val binding = ItemLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LabelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LabelViewHolder(private val binding: ItemLabelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(label: Label) {
            binding.label = label
        }
    }
}

class LabelDiffUtil : DiffUtil.ItemCallback<Label>() {
    override fun areItemsTheSame(oldItem: Label, newItem: Label): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Label, newItem: Label): Boolean {
        return oldItem == newItem
    }

}