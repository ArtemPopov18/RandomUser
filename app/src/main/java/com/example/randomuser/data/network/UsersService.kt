package com.example.randomuser.data.network

import com.example.randomuser.data.model.PageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {

    @GET("?results=10&seed=abc")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<PageResponse>
}