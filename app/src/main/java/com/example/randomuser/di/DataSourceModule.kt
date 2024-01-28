package com.example.randomuser.di

import androidx.paging.PagingSource
import com.example.randomuser.data.datasource.UserPagingDataSource
import com.example.randomuser.data.model.Result
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserPagingDataSource(
        dataSource: UserPagingDataSource
    ): PagingSource<Int, Result>
}