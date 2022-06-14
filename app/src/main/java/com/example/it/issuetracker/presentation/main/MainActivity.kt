package com.example.it.issuetracker.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.it.issuetracker.R
import com.example.it.issuetracker.databinding.ActivityMainBinding
import com.example.it.issuetracker.presentation.main.issue.IssueFragment
import com.example.it.issuetracker.presentation.main.label.LabelFragment
import com.example.it.issuetracker.presentation.main.milestone.MilestoneFragment
import com.example.it.issuetracker.presentation.main.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val issueFragment = IssueFragment()
    private val labelFragment = LabelFragment()
    private val milestoneFragment = MilestoneFragment()
    private val myPageFragment = MyPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigationMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.issueFragment -> {
                    changeFragment(issueFragment, "issue")
                }
                R.id.labelFragment -> {
                    changeFragment(labelFragment, "label")
                }
                R.id.milestoneFragment -> {
                    changeFragment(milestoneFragment, "milestone")
                }
                R.id.myPageFragment -> {
                    changeFragment(myPageFragment, "my_page")
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment, fragmentName: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, fragment)
            .addToBackStack(fragmentName)
            .commit()
    }
}