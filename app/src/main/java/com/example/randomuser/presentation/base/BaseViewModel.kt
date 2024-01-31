package com.example.randomuser.presentation.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    abstract fun onResult(result: Any?)
}
