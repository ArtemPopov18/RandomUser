package com.example.randomuser.domain.mapper

import android.util.Log
import com.example.randomuser.data.model.Result
import com.example.randomuser.domain.mapper.base.Mapper
import com.example.randomuser.domain.model.Coordinates
import com.example.randomuser.domain.model.Dob
import com.example.randomuser.domain.model.Location
import com.example.randomuser.domain.model.Name
import com.example.randomuser.domain.model.Picture
import com.example.randomuser.domain.model.Street
import com.example.randomuser.domain.model.User
import com.example.randomuser.domain.model.getUserGenderEnum
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<Result, User>() {
    override fun map(from: Result) = from.run {
        User(
            dob = Dob(dob?.age, dob?.date),
            email = email,
            gender = getUserGenderEnum(gender),
            id = id?.value,
            phone = phone,
            picture = Picture(picture?.large, picture?.medium),
            location = Location(
                location?.city,
                location?.country,
                location?.state,
                Street(location?.street?.name, location?.street?.number),
                Coordinates(location?.coordinates?.latitude, location?.coordinates?.longitude)
            ),
            name = Name(name?.first, name?.last, name?.title)
        )
    }
}