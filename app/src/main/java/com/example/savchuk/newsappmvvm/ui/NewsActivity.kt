package com.example.savchuk.newsappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.savchuk.newsappmvvm.R
import com.example.savchuk.newsappmvvm.databinding.ActivityNewsBinding
import com.example.savchuk.newsappmvvm.db.ArticleDataBase
import com.example.savchuk.newsappmvvm.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    //lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NewsRepository.initialize(this)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* val repository = NewsRepository(ArticleDataBase(this))
        val viewModelProviderFactory = NewsViewModelProvideFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
      // viewModel = ViewModelProvider(this, viewModelProvideFactory).get(NewsViewModel::class.java )*/

        /*val newsRepository = NewsRepository(this)
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)*/


        val newsNavHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())


    }
}