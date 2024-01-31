package com.example.randomuser.di

import com.example.randomuser.presentation.navigator.Navigator
import com.example.randomuser.presentation.navigator.NavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Provides
    fun provideNavigator(): Navigator {
        return NavigatorImpl()
    }
}