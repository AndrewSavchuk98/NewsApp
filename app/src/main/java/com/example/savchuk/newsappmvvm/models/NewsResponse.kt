package com.example.savchuk.newsappmvvm.models

import com.example.savchuk.newsappmvvm.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)