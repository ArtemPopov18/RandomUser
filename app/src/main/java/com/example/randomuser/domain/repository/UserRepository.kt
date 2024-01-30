package com.example.randomuser.domain.repository

import androidx.paging.PagingData
import com.example.randomuser.data.model.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<Result>>
}