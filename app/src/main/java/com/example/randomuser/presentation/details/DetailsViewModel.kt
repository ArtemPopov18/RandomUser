package com.example.randomuser.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomuser.presentation.base.Event
import com.example.randomuser.presentation.model.UserUI
import com.example.randomuser.presentation.details.DetailsFragment.Screen

class DetailsViewModel(
    screen: Screen
) : ViewModel() {

    private val _initialUserEvent = MutableLiveData<Event<UserUI>>()
    val initialUserEvent: LiveData<Event<UserUI>> = _initialUserEvent

    init {
        _initialUserEvent.value = Event(screen.initialUser)
    }
}
