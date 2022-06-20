package com.example.issu_tracker.ui.common

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.issu_tracker.R
import com.example.issu_tracker.data.Label
import com.example.issu_tracker.databinding.ItemLabelBinding


class LabelAdapter(private val labels: List<Label>) :
    RecyclerView.Adapter<LabelAdapter.LabelViewHolder>() {
    inner class LabelViewHolder(val binding: ItemLabelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Label) {
            binding.tvLabelContent.text = item.content

            val background = ContextCompat.getDrawable(
                binding.tvLabelContent.context,
                R.drawable.round_rectangle_background
            ) as GradientDrawable

            background.setColor(Color.parseColor(item.color))

            binding.tvLabelContent.background = background
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        return LabelViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_label,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(labels[position])
    }

    override fun getItemCount(): Int {
        return labels.size
    }
}