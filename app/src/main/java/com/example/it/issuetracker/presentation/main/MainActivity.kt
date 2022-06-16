package com.example.it.issuetracker.presentation.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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

        val imageUrl = intent.getStringExtra("imageUrl")
        Glide.with(this).asDrawable().load(imageUrl)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?,
                ) {
                    val menu = binding.bottomNavigationMain.menu.findItem(R.id.myPageFragment)
                    menu.icon = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit
            })


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