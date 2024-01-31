package com.example.randomuser.presentation.navigator

import com.example.randomuser.presentation.base.BaseScreen

interface Navigator {

    fun launch(screen: BaseScreen)

    fun goBack()
}