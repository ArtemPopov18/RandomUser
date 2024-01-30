package com.example.randomuser.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.randomuser.data.datasource.UserPagingDataSource
import com.example.randomuser.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserPagingDataSource
) : UserRepository {

    override fun getUsers() = Pager(PagingConfig(pageSize = 10)) { dataSource }.flow
}