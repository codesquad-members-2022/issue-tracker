package com.example.issu_tracker.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.issu_tracker.R
import com.example.issu_tracker.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

var userUid = ""

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO User UID 를 로그인 에서 넘겨 받는 작업 필요
        userUid = "aU0xWZ9AQMYHt26ZAOX1ur79cFk1"

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            findNavController(R.id.nav_host)
        )

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.nav_host))

    }
}