package com.example.issue_tracker.ui

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.issue_tracker.R
import com.example.issue_tracker.databinding.ActivityHomeBinding
import com.example.issue_tracker.ui.issue.IssueFragment
import com.example.issue_tracker.ui.issue.IssueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: IssueViewModel by viewModels()
    private val issueFragment = IssueFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun onStart() {
        super.onStart()
        binding.issueBottomNavigation.setupWithNavController(findNavController(R.id.nav_home_fragment))
    }

    // 화면 아무 곳이나 클릭 하면 키보드 숨김
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }
}