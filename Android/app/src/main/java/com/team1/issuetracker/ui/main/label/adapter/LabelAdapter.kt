package com.team1.issuetracker.ui.main.label.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team1.issuetracker.R
import com.team1.issuetracker.data.model.Label
import com.team1.issuetracker.databinding.ItemLabelBinding

class LabelAdapter(private val longClick: () -> Unit): ListAdapter<Label, LabelAdapter.LabelViewHolder>(
    LabelDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        return LabelViewHolder(ItemLabelBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LabelViewHolder(private val binding: ItemLabelBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(label: Label) {
            binding.item = label

            binding.clCheckbox.isVisible = label.isCheckVisible

            // 뷰홀더 재사용 과정에서 isClamped 값에 맞지 않는 스와이프 상태가 보일 수 있으므로 아래와 같이 명시적으로 isClamped 값에 따라 스와이프 상태 지정
            if (label.isSwiped) binding.labelView.translationX = binding.root.width * -1f / 10 * 3
            else binding.labelView.translationX = 0f

            binding.eraseItemView.setOnClickListener {
                if (label.isSwiped) removeItem(adapterPosition)
            }

            binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    getItem(adapterPosition).isChecked = true
                    binding.labelView.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.Backgrounds2))
                } else {
                    getItem(adapterPosition).isChecked = false
                    binding.labelView.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
                }
            }

            binding.root.setOnLongClickListener(View.OnLongClickListener {
                val pos = adapterPosition
                Log.d("AppTest", "long click pos : $pos")

                longClick.invoke()

                return@OnLongClickListener true
            })
        }

        fun setClamped(isClamped: Boolean) {
            getItem(adapterPosition).isSwiped = isClamped
        }

        fun getClamped(): Boolean {
            return getItem(adapterPosition).isSwiped
        }

    }

    fun removeItem(position: Int) {  // currentList에서 바로 아이템 지우면 에러 발생
        val newList = currentList.toMutableList()
        newList.removeAt(position)

        newList.forEach {
            it.isSwiped = false
        } // 한 아이템 삭제 시 다른 아이템들 모두 스와이프x 상태 처리하기 위함

        submitList(newList.toList())
    }

    fun makeCheckBoxVisible() {
        val newList = ArrayList<Label>()
        currentList.forEach {
            newList.add(
                Label(
                    id = it.id,
                    color = it.color,
                    title = it.title,
                    description = it.description,
                    isCheckVisible = true
                )
            )
        }
        submitList(newList.toList())
    }

    fun makeCheckBoxGone() {
        val newList = ArrayList<Label>()
        currentList.forEach {
            newList.add(
                Label(
                    id = it.id,
                    color = it.color,
                    title = it.title,
                    description = it.description
                )
            )
        }
        submitList(newList.toList())

    }

    private object LabelDiffUtil: DiffUtil.ItemCallback<Label>() {

        override fun areItemsTheSame(oldItem: Label, newItem: Label) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Label, newItem: Label) =
            oldItem == newItem

    }

}