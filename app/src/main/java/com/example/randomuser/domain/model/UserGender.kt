package com.example.randomuser.domain.model

enum class UserGender {
    FEMALE, MALE, UNKNOWN
}

fun getUserGenderEnum(gender: String?) =
    when (gender) {
        "female" -> UserGender.FEMALE
        "male" -> UserGender.MALE
        else -> UserGender.UNKNOWN
    }