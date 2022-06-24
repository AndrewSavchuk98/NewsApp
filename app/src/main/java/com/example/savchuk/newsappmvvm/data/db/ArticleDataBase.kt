package com.example.savchuk.newsappmvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.savchuk.newsappmvvm.data.models.Article
import com.example.savchuk.newsappmvvm.utils.Constants.Companion.VERSION_DATA_BASE

@Database(
    entities = [
        Article::class
    ],
    version = VERSION_DATA_BASE
)
@TypeConverters(Converters::class)
abstract class ArticleDataBase :RoomDatabase(){

    abstract fun getArticleDao() :ArticleDAO
}