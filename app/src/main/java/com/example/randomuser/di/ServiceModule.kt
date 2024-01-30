package com.example.randomuser.di

import com.example.randomuser.data.network.UsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideUserService(retrofit: Retrofit): UsersService =
        retrofit.create(UsersService::class.java)
}