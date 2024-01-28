package com.example.randomuser.domain.model

data class User(
    val dob: Dob,
    val email: String,
    val gender: UserGender,
    val phone: String,
    val picture: Picture,
    val location: Location,
    val name: Name,
)

data class Name (
    val first: String,
    val last: String,
    val title: String
)

data class Location(
    val city: String,
    val country: String,
    val state: String,
    val street: Street,
)

data class Street(
    val name: String,
    val number: Int,
)

data class Picture(
    val large: String,
    val medium: String,
)

data class Dob(
    val age: Int,
    val date: String,
)
