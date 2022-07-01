package com.example.it.issuetracker.presentation.main.milestone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.MilestoneItemBinding
import com.example.it.issuetracker.domain.model.MileStone
import com.example.it.issuetracker.presentation.main.issue.list.Mode

class MilestoneAdapter(
    private val onEditMode: (editMode: Boolean) -> Unit,
    private val onClick: (milestone: MileStone) -> Unit,
) : ListAdapter<MileStone, MilestoneAdapter.MilestoneViewHolder>(diffUtil) {

    class MilestoneViewHolder(
        private val binding: MilestoneItemBinding,
        private val onEditMode: (editMode: Boolean) -> Unit,
        private val onClick: (milestone: MileStone) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(milestone: MileStone) {
            binding.milestoneInfo = milestone
            setItemClickListener(milestone)
            setItemLayoutChange(milestone)
        }

        private fun setItemLayoutChange(milestone: MileStone) {
            binding.cbEdit.setOnCheckedChangeListener { _, isChecked ->
                milestone.isChecked = isChecked
            }

            when (milestone.mode) {
                Mode.DEFAULT -> binding.cbEdit.isVisible = false
                Mode.EDIT -> binding.cbEdit.isVisible = true
            }
        }

        private fun setItemClickListener(milestone: MileStone) {
            if (milestone.mode == Mode.DEFAULT) {
                itemView.setOnClickListener {
                    onClick(milestone)
                }
            } else {
                itemView.setOnClickListener {
                    binding.cbEdit.isChecked = !binding.cbEdit.isChecked
                }
            }
            itemView.setOnLongClickListener {
                onEditMode(true)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilestoneViewHolder {
        return MilestoneViewHolder(
            MilestoneItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onEditMode,
            onClick
        )
    }

    override fun onBindViewHolder(holder: MilestoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<MileStone>() {
    override fun areItemsTheSame(oldItem: MileStone, newItem: MileStone): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MileStone, newItem: MileStone): Boolean {
        return oldItem == newItem
    }
}