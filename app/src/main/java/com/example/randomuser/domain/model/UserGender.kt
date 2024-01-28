package com.example.randomuser.domain.model

enum class UserGender {
    FEMALE, MALE, UNKNOWN
}

fun getUserGenderEnum(gender: String) =
    when (gender) {
        "famele" -> UserGender.FEMALE
        "male" -> UserGender.MALE
        else -> UserGender.UNKNOWN
    }