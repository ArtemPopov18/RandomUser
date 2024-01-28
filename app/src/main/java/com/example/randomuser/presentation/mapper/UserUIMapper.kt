package com.example.randomuser.presentation.mapper

import com.example.randomuser.domain.mapper.base.Mapper
import com.example.randomuser.domain.model.User
import com.example.randomuser.presentation.model.DobUI
import com.example.randomuser.presentation.model.LocationUI
import com.example.randomuser.presentation.model.NameUI
import com.example.randomuser.presentation.model.PictureUI
import com.example.randomuser.presentation.model.StreetUI
import com.example.randomuser.presentation.model.UserUI
import com.example.randomuser.presentation.model.convertToUI
import javax.inject.Inject

class UserUIMapper @Inject constructor() : Mapper<User, UserUI>() {
    override fun map(from: User) = from.run {
        UserUI(
            dob = DobUI(dob.age, dob.date),
            email = email,
            gender = gender.convertToUI(),
            phone = phone,
            picture = PictureUI(picture.large, picture.medium),
            location = LocationUI(
                location.city,
                location.country,
                location.state,
                StreetUI(location.street.name, location.street.number)
            ),
            name = NameUI(name.first, name.last, name.title)
        )
    }
}