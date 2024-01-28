package com.example.randomuser.presentation.model

import android.content.Context

data class UserUI(
    val dob: DobUI,
    val email: String,
    val gender: UserGenderUI,
    val phone: String,
    val picture: PictureUI,
    val location: LocationUI,
    private val name: NameUI,
) {
    fun getUserName() = "Hi, My name is ${name.title}. ${name.first} ${name.last}"

    fun getColor() = gender.color
}

data class DobUI(
    val age: Int,
    val date: String,
)

data class PictureUI(
    val large: String,
    val medium: String,
)

data class LocationUI(
    val city: String,
    val country: String,
    val state: String,
    val street: StreetUI,
)

data class StreetUI(
    val name: String,
    val number: Int,
)

data class NameUI(
    val first: String,
    val last: String,
    val title: String,
)
