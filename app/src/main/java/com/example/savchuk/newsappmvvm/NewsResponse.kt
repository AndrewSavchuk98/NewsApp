package com.example.savchuk.newsappmvvm

import com.example.savchuk.newsappmvvm.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)