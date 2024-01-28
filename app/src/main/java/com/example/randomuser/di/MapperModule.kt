package com.example.randomuser.di

import com.example.randomuser.data.model.Result
import com.example.randomuser.domain.mapper.UserMapper
import com.example.randomuser.domain.mapper.base.Mapper
import com.example.randomuser.domain.model.User
import com.example.randomuser.presentation.mapper.UserUIMapper
import com.example.randomuser.presentation.model.UserUI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindUserMapper(
        mapper: UserMapper
    ): Mapper<Result, User>

    @Binds
    abstract fun bindUserUIMapper(
        mapper: UserUIMapper
    ): Mapper<User, UserUI>
}