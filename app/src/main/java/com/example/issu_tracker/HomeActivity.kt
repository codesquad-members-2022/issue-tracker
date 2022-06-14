package com.example.issu_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.issu_tracker.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this  , R.layout.activity_home )

        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            findNavController(R.id.nav_host)
        )

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.nav_host))

    }
}