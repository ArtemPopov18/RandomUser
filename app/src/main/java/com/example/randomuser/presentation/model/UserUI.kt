package com.example.randomuser.presentation.model

import android.net.Uri
import java.io.Serializable

data class UserUI(
    val dob: DobUI?,
    val email: String?,
    val gender: UserGenderUI?,
    val id: String?,
    val phone: String?,
    val picture: PictureUI?,
    private val location: LocationUI?,
    private val name: NameUI?,
) : Serializable {
    fun getUserName() = "Hi, My name is ${name?.title}. ${name?.first} ${name?.last}"

    fun getColorTextName() = gender?.color

    fun getLocation() =
        "${location?.country} - ${location?.state} - ${location?.city} - \n${location?.street?.name} - ${location?.street?.number}"

    fun geoLocationUri(): Uri =
        Uri.parse("geo:${location?.coordinates?.latitude},${location?.coordinates?.longitude}")
}

data class DobUI(
    val age: Int?,
    val date: String?,
) : Serializable

data class PictureUI(
    val large: String?,
    val medium: String?,
) : Serializable

data class LocationUI(
    val city: String?,
    val country: String?,
    val state: String?,
    val street: StreetUI?,
    val coordinates: CoordinatesUI?,
) : Serializable

data class CoordinatesUI(
    val latitude: String?,
    val longitude: String?,
) : Serializable

data class StreetUI(
    val name: String?,
    val number: Int?,
) : Serializable

data class NameUI(
    val first: String?,
    val last: String?,
    val title: String?,
) : Serializable
