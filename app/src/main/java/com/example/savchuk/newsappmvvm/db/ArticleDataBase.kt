package com.example.savchuk.newsappmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.savchuk.newsappmvvm.models.Article
import com.example.savchuk.newsappmvvm.utils.Constantes.Companion.NAME_DATABASE
import com.example.savchuk.newsappmvvm.utils.Constantes.Companion.VERSION_DATA_BASE

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