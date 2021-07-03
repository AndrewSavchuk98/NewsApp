package com.example.savchuk.newsappmvvm.ui

import androidx.lifecycle.ViewModel
import com.example.savchuk.newsappmvvm.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {
}