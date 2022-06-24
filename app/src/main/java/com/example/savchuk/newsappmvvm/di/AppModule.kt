package com.example.savchuk.newsappmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.savchuk.newsappmvvm.data.db.ArticleDAO
import com.example.savchuk.newsappmvvm.data.db.ArticleDataBase
import com.example.savchuk.newsappmvvm.data.remote.NewsAPI
import com.example.savchuk.newsappmvvm.utils.Constants
import com.example.savchuk.newsappmvvm.utils.Constants.Companion.API_KEY
import com.example.savchuk.newsappmvvm.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun providedOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("apiKey", API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherRetrofit(base_url: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsAPI = retrofit.create(NewsAPI::class.java)

    @Provides
    fun provideChannelDao(appDatabase: ArticleDataBase): ArticleDAO {
        return appDatabase.getArticleDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ArticleDataBase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            ArticleDataBase::class.java,
            Constants.NAME_DATABASE
        ).build()
    }
}