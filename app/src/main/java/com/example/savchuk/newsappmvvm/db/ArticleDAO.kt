package com.example.savchuk.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.savchuk.newsappmvvm.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: Article) : Long

    @Query("SELECT * FROM articles")
    fun getAllArticles() : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}