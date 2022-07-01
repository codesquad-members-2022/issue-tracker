package com.team1.issuetracker.ui.main.issue

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.team1.issuetracker.R
import com.team1.issuetracker.common.PrintLog
import com.team1.issuetracker.data.model.Issue
import com.team1.issuetracker.databinding.FragmentIssueBinding
import com.team1.issuetracker.ui.main.MainActivity
import com.team1.issuetracker.ui.main.issue.adapter.IssueListAdapter
import com.team1.issuetracker.common.SwipeHelper
import com.team1.issuetracker.ui.main.issue.adapter.IssueSwipeHelper
import kotlinx.coroutines.launch

class IssueFragment : Fragment() {

    private lateinit var binding: FragmentIssueBinding
    private lateinit var issueListAdapter: IssueListAdapter

    private var actionMode: ActionMode? = null

    private val viewModel: IssueViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue, container, false)
        val view = binding.root

        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeHelper = IssueSwipeHelper()
        val itemTouchHelper = ItemTouchHelper(swipeHelper)
        itemTouchHelper.attachToRecyclerView(binding.rvIssue)


        val callback = object : ActionMode.Callback {
            var cancel = true

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("AppTest", "onCreateActionMode")
                requireActivity().menuInflater.inflate(R.menu.contextual_action_bar, menu)
                cancel = true
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("AppTest", "onPrepareActionMode")
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.delete -> {
                        // Handle delete icon press
                        cancel = false
                        true
                    }
                    R.id.close -> {
                        // Handle close icon press
                        cancel = false
                        //viewModel.requestCloseIssueSet()
                        mode?.finish()
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                Log.d("AppTest", "onDestroyActionMode")
                actionMode = null

                issueListAdapter.makeCheckBoxGone() // 모든 아이템 체크박스 보이지 않도록
                if(cancel) viewModel.checkedSetClear()
                else viewModel.requestCloseIssueSet()

                itemTouchHelper.attachToRecyclerView(binding.rvIssue) // 다시 헬퍼 붙이기
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemCount.collect {
                    val count = it
                    actionMode?.let {
                        it.title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            Html.fromHtml(
                                "<font color='#FFFFFF'>${count}</font>",
                                Html.FROM_HTML_MODE_LEGACY
                            )
                        else
                            Html.fromHtml("<font color='#FFFFFF'>${count}</font>")
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.closeOrDeleteFlow.collect{
                    PrintLog.printLog("sharedflow collect")
                    if(it) showSnackbar()
                }
            }
        }


        setAppBar()

        //////////////////////////////////////////////

        issueListAdapter = IssueListAdapter({
            // 아이템 롱 클릭
            PrintLog.printLog("Issue Item Long Click")    // 이슈 리스트 아이템 롱 클릭 이벤트 영역!!

            actionMode = (activity as AppCompatActivity).startSupportActionMode(callback)  // ? 맞는 방법

            actionMode?.let {
                it.title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    Html.fromHtml(
                        "<font color='#FFFFFF'>${viewModel.itemCount.value}</font>",
                        Html.FROM_HTML_MODE_LEGACY
                    )
                else
                    Html.fromHtml("<font color='#FFFFFF'>${viewModel.itemCount.value}</font>")
            }

            itemTouchHelper.attachToRecyclerView(null) // 체크 박스 활성화 상태에서는 스와이프 안되게
            issueListAdapter.makeCheckBosVisible() // 모든 아이템 체크박스 보이도록
        },
            {
                // 체크박스
                viewModel.checkItem(it)
            },
            {
                // 스와이프 후 해당 아이템 close
                viewModel.requestCloseSpecificIssue(it)
            })

        binding.rvIssue.apply {
            adapter = issueListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setOnTouchListener { v, event ->
                swipeHelper.removePreviousClamp(this)
                false
            }
        }

        val animator = binding.rvIssue.itemAnimator     //리사이클러뷰 애니메이터 get
        if (animator is SimpleItemAnimator) {          //아이템 애니메이커 기본 하위클래스
            animator.supportsChangeAnimations =
                false  //애니메이션 값 false (리사이클러뷰가 화면을 다시 갱신 했을때 뷰들의 깜빡임 방지)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.issueList.collect {
                    issueListAdapter.submitList(it.toList())
                }
            }
        }

        //////////////////////////////////////////////////

    }

    private fun setAppBar() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.issue_search -> {
                    PrintLog.printLog("issue search")
                    true
                }
                else -> false
            }
        }

//        binding.topAppBar.setNavigationOnClickListener {
//            PrintLog.printLog("issue filter")
//            if (binding.cloFilterLayout.visibility == View.VISIBLE) {
//                binding.cloFilterLayout.visibility = View.GONE
//                binding.imgMore1.animate().setDuration(200).rotation(180f)
//            } else {
//                binding.cloFilterLayout.visibility = View.VISIBLE
//                binding.imgMore1.animate().setDuration(200).rotation(0f)
//                changeAppbar()
//            }
//        }
    }

    private fun changeAppbar() {
        binding.topAppBar.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.Primary1
            )
        )
        binding.topAppBar.inflateMenu(R.menu.filter_appbar_menu)
        binding.topAppBar.title = "필터"
        binding.topAppBar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.topAppBar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_cancel)
    }

    private fun showSnackbar(){
        val snackbar = Snackbar.make(requireActivity().findViewById(R.id.cl_main), "선택한 이슈를 닫았습니다.", Snackbar.LENGTH_LONG)
        snackbar.setAction("실행취소"
        ) {
            PrintLog.printLog("실행취소")
            viewModel.undo()
        }
            .addCallback(object: Snackbar.Callback(){ // 스낵바 사라지는 시점 체크
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)

                    PrintLog.printLog("snackbar dismissed")
                    viewModel.checkedSetClear()
                }
            })
        snackbar.show()
    }


}