package com.team1.issuetracker.ui.main.milestone.adapter

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
import com.team1.issuetracker.common.PrintLog
import com.team1.issuetracker.data.model.Milestone
import com.team1.issuetracker.databinding.ItemMilestoneBinding

class MilestoneAdapter(
    private val longClick: () -> Unit,
    private val itemCheck: (Int) -> Unit,
    private val closeSwiped: (Int) -> Unit
) :
    ListAdapter<Milestone, MilestoneAdapter.MilestoneViewHolder>(MilestoneDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilestoneViewHolder {
        return MilestoneViewHolder(
            ItemMilestoneBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MilestoneViewHolder, position: Int) {
        holder.bind(getItem(position), longClick, itemCheck, closeSwiped)
    }

    inner class MilestoneViewHolder(private val binding: ItemMilestoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(milestone: Milestone, longClick: () -> Unit, itemCheck: (Int) -> Unit, closeSwiped: (Int) -> Unit) {
            Log.d("AppTest", "bind")
            binding.clCheckbox.isVisible = milestone.isCheckVisible
            binding.item = milestone
            binding.tvOpenIssueCount.text = binding.root.context.getString(R.string.open_issue_count_label, milestone.open)
            binding.tvClosedIssueCount.text = binding.root.context.getString(R.string.closed_issue_count_label, milestone.closed)


            // 뷰홀더 재사용 과정에서 isClamped 값에 맞지 않는 스와이프 상태가 보일 수 있으므로 아래와 같이 명시적으로 isClamped 값에 따라 스와이프 상태 지정
            if (milestone.isSwiped) binding.milestoneView.translationX = binding.root.width * -1f / 10 * 3
            else binding.milestoneView.translationX = 0f

            binding.eraseItemView.setOnClickListener {
                PrintLog.printLog("${milestone}")
                PrintLog.printLog("${getItem(adapterPosition)}")
                if (getItem(adapterPosition).isSwiped) { // issue.isSwiped에서 변경!!
                    PrintLog.printLog("issue adapter/ swiped click : ${adapterPosition}, ${getItem(adapterPosition).id}")
                    closeSwiped.invoke(getItem(adapterPosition).id)
                }
                else{
                    PrintLog.printLog("issue adapter/ not swiped : ${adapterPosition}, ${getItem(adapterPosition).id}, ${getItem(adapterPosition).isSwiped}")
                }
            }
            // 체크 여부에 따른 배경색 설정
            if (milestone.isChecked) {
                binding.checkbox.isChecked = true
                binding.milestoneView.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.Backgrounds2
                    )
                )
            } else {
                binding.checkbox.isChecked = false
                binding.milestoneView.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )
            }

            binding.checkbox.setOnClickListener {
                if(binding.checkbox.isChecked){
                    PrintLog.printLog("checked")
                    getItem(adapterPosition).isChecked = true
                    binding.milestoneView.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.Backgrounds2
                        )
                    )
                }
                else{
                    PrintLog.printLog("unchecked")
                    getItem(adapterPosition).isChecked = false
                    binding.milestoneView.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.white
                        )
                    )
                }
                itemCheck.invoke(adapterPosition) // IssueFragment에서 뷰모델과 연결된다, 뷰모델에는 open 상태 아닌 아이템도 다 관리하므로 issueId 넘기기!
            }
            // 직접 체크 박스 클릭하는 경우만 고려하기 위해 setOnCheckedChangeListener 대신 onClickListene 사용

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
        val newList = ArrayList<Milestone>()
        currentList.forEach {
            newList.add(
                Milestone(
                    id = it.id,
                    title = it.title,
                    progress = it.progress,
                    content = it.content,
                    date = it.date,
                    open = it.open,
                    closed = it.closed,
                    isCheckVisible = true
                )
            )
        }
        submitList(newList.toList())
    }

    fun makeCheckBoxGone() {
        val newList = ArrayList<Milestone>()
        currentList.forEach {
            newList.add(
                Milestone(
                    id = it.id,
                    title = it.title,
                    progress = it.progress,
                    content = it.content,
                    date = it.date,
                    open = it.open,
                    closed = it.closed
                )
            )
        }
        submitList(newList.toList())
    }

    private object MilestoneDiffUtil : DiffUtil.ItemCallback<Milestone>() {

        override fun areItemsTheSame(oldItem: Milestone, newItem: Milestone) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Milestone, newItem: Milestone) =
            oldItem == newItem
    }

}