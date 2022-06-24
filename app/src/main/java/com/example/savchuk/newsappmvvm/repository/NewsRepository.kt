package com.example.savchuk.newsappmvvm.repository

import com.example.savchuk.newsappmvvm.data.db.ArticleDAO
import com.example.savchuk.newsappmvvm.data.remote.NewsAPI
import com.example.savchuk.newsappmvvm.data.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsAPI,
    private val database: ArticleDAO
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = database.upsertArticle(article)

    fun getSavedNews() = database.getAllArticles()

    suspend fun deleteArticle(article: Article) = database.deleteArticle(article)

}