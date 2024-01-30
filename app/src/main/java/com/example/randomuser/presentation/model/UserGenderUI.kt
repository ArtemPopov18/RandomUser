package com.example.randomuser.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.randomuser.R
import com.example.randomuser.domain.model.UserGender

enum class UserGenderUI(
    @ColorRes
    val color: Int,
    @StringRes
    val naming: Int
) {
    FEMALE(
        R.color.pink,
        R.string.female
    ),
    MALE(
        R.color.blue,
        R.string.male
    ),
    UNKNOWN(
        R.color.tan,
        R.string.unknown
    )
}

fun UserGender.convertToUI(): UserGenderUI {
    return when (this) {
        UserGender.FEMALE -> UserGenderUI.FEMALE
        UserGender.MALE -> UserGenderUI.MALE
        UserGender.UNKNOWN -> UserGenderUI.UNKNOWN
    }
}