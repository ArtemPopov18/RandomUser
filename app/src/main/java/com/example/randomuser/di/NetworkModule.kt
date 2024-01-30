package com.example.randomuser.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun baseURl(): String = "https://randomuser.me/api/"

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient(logging : HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(baseURl: String, okHttpClient: OkHttpClient, logging: HttpLoggingInterceptor): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURl)
            .client(okHttpClient(logging))
            .build()
}