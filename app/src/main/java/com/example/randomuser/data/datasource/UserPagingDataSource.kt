package com.example.randomuser.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randomuser.data.model.Result
import com.example.randomuser.data.network.UsersService
import retrofit2.HttpException
import javax.inject.Inject

class UserPagingDataSource @Inject constructor(private val service: UsersService) :
    PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val pageNumber = params.key ?: 2
            val response = service.getUsers(pageNumber)
            if (response.isSuccessful) {
                val pageResponse = response.body()
                val data = pageResponse?.results
                val nextPageNumber: Int? = pageResponse?.info?.page?.plus(1)
                val prevPageNumber: Int? = pageResponse?.info?.page?.minus(1)

                LoadResult.Page(
                    data = data.orEmpty(),
                    nextKey = nextPageNumber,
                    prevKey = prevPageNumber
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            throw e
//            Log.d("logMy", "${e.message}")
//            LoadResult.Error(e)
        }
    }
}