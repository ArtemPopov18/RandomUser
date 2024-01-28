package com.example.randomuser.data.datasource

import com.example.randomuser.data.model.Result
import com.example.randomuser.data.network.UsersService
import javax.inject.Inject

class UserPagingDataSource @Inject constructor (private val service: UsersService) : PagingSource<Int, Result> {

}