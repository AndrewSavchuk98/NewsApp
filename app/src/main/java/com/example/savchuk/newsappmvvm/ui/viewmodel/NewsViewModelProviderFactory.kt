package com.example.savchuk.newsappmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.savchuk.newsappmvvm.repository.NewsRepository

class NewsViewModelProviderFactory(
) : ViewModelProvider.Factory {
    val newsRepository = NewsRepository.get()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel() as T
    }
}