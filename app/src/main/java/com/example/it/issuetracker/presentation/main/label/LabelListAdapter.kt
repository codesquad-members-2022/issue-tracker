package com.example.it.issuetracker.presentation.main.label

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.it.issuetracker.databinding.LabelItemBinding
import com.example.it.issuetracker.domain.model.Label
import com.example.it.issuetracker.presentation.main.issue.list.Mode

class LabelListAdapter(
    private val editModeListener: (editMode: Boolean) -> Unit,
    private val itemClickListener: (label: Label) -> Unit,
) : ListAdapter<Label, LabelListAdapter.LabelViewHolder>(diffUtil) {

    class LabelViewHolder(
        private val binding: LabelItemBinding,
        private val editModeListener: (editMode: Boolean) -> Unit,
        private val itemClickListener: (label: Label) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(label: Label) {
            binding.labelInfo = label
            setItemClickListener(label)
            setItemLayoutChange(label)
        }

        private fun setItemLayoutChange(label: Label) {
            binding.cbEdit.setOnCheckedChangeListener { _, isChecked ->
                label.isChecked = isChecked
            }

            when (label.mode) {
                Mode.DEFAULT -> binding.cbEdit.isVisible = false
                Mode.EDIT -> binding.cbEdit.isVisible = true
            }
        }

        private fun setItemClickListener(label: Label) {
            if (label.mode == Mode.DEFAULT) {
                itemView.setOnClickListener {
                    itemClickListener(label)
                }
            } else {
                itemView.setOnClickListener {
                    binding.cbEdit.isChecked = !binding.cbEdit.isChecked
                }
            }
            itemView.setOnLongClickListener {
                editModeListener(true)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        return LabelViewHolder(
            LabelItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            editModeListener,
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Label>() {
    override fun areItemsTheSame(oldItem: Label, newItem: Label): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Label, newItem: Label): Boolean {
        return oldItem.id == newItem.id
    }
}