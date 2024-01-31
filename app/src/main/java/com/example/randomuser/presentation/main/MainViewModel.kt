package com.example.randomuser.presentation.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.randomuser.domain.interactor.GetUserUseCase
import com.example.randomuser.domain.mapper.base.Mapper
import com.example.randomuser.domain.model.User
import com.example.randomuser.presentation.base.BaseViewModel
import com.example.randomuser.presentation.details.DetailsFragment
import com.example.randomuser.presentation.model.UserUI
import com.example.randomuser.presentation.navigator.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.randomuser.presentation.main.MainFragment.Screen

@HiltViewModel
class MainViewModel @Inject constructor(
    getUserUseCase: GetUserUseCase,
    private val mapper: Mapper<User, UserUI>,
    private val navigator: Navigator,
    screen: Screen
) : BaseViewModel() {

    private val _user: MutableStateFlow<PagingData<UserUI>> = MutableStateFlow(PagingData.empty())
    val user: StateFlow<PagingData<UserUI>> = _user

    init {
        viewModelScope.launch {
            getUserUseCase.invoke()
                .map { pagingData ->
                    pagingData.map { user ->
                        mapper.map(user)
                    }
                }
                .cachedIn(viewModelScope)
                .collectLatest {
                    _user.value = it
                }
        }
    }

    fun onClickItem(userUI: UserUI){
        navigator.launch(DetailsFragment.Screen(initialUser = userUI))
    }

    override fun onResult(result: Any?) {

    }
}