package com.example.savchuk.newsappmvvm.db

import androidx.room.TypeConverter
import com.example.savchuk.newsappmvvm.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}