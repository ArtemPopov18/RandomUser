package com.example.randomuser.domain.interactor

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.map
import com.example.randomuser.data.model.Result
import com.example.randomuser.domain.mapper.base.Mapper
import com.example.randomuser.domain.model.User
import com.example.randomuser.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository,
    private val mapper: Mapper<Result, User>
) {

    fun invoke(): Flow<PagingData<User>> =
        repository.getUsers().map { pagingData ->
            pagingData.map { userRepository ->
                mapper.map(userRepository)
            }
        }
}