package com.example.randomuser.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.randomuser.domain.interactor.GetUserUseCase
import com.example.randomuser.domain.mapper.base.Mapper
import com.example.randomuser.domain.model.User
import com.example.randomuser.presentation.model.UserUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ManeViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase,
    private val mapper: Mapper<User, UserUI>
) : ViewModel() {

    val user: StateFlow<PagingData<UserUI>> =
        getUserUseCase.invoke().map { pagingData ->
            pagingData.map { user ->
                mapper.map(user)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            PagingData.empty()
        )
}