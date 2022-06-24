package com.example.savchuk.newsappmvvm.data.models

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)