package com.example.savchuk.newsappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.savchuk.newsappmvvm.R
import com.example.savchuk.newsappmvvm.databinding.ActivityNewsBinding
import com.example.savchuk.newsappmvvm.repository.NewsRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}