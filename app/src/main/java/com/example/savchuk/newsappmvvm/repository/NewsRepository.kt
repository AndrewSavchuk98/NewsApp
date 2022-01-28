package com.example.savchuk.newsappmvvm.repository

import android.content.Context
import androidx.room.Room
import com.example.savchuk.newsappmvvm.api.RetrofitInstance
import com.example.savchuk.newsappmvvm.db.ArticleDataBase
import com.example.savchuk.newsappmvvm.models.Article
import com.example.savchuk.newsappmvvm.utils.Constantes
import java.lang.IllegalStateException

class NewsRepository(
   context: Context
) {

    private val database =  Room.databaseBuilder(
        context.applicationContext,
        ArticleDataBase::class.java,
        Constantes.NAME_DATABASE
    ).build()
    //private val articleDAO = database.getArticleDao()

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int)=
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = database.getArticleDao().upsertArticle(article)

    fun getSavedNews() = database.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = database.getArticleDao().deleteArticle(article)

    companion object{
        //@Volatile
        private var INSTANCE : NewsRepository? = null
        private val LOCK = Any()

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NewsRepository(context)
            }
        }

        fun get(): NewsRepository {
            return INSTANCE
                ?: throw IllegalStateException("News repository must be initialized")
        }
    }

}