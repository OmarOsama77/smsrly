package com.example.smsrly.data.mapper

import com.example.smsrly.data.local.db.entities.UserEntity
import com.example.smsrly.data.remote.dto.login.LoginSuccessDto
import com.example.smsrly.data.remote.dto.signup.SignupSuccessDto
import com.example.smsrly.data.remote.dto.user.UserDto
import com.example.smsrly.domain.models.AuthTokens
import com.example.smsrly.domain.models.User


fun SignupSuccessDto.toDomain(): AuthTokens{
    return AuthTokens(
        id,
        access_token,
        refresh_token
    )
}
fun LoginSuccessDto.toDomain(): AuthTokens{
    return AuthTokens(
        id,
        access_token,
        refresh_token
    )
}



fun UserDto.toDomain(): User{
    return User(
        firstName = firstname,
        lastName = lastname,
        email  = email,
        latitude = latitude,
        longitude = longitude,
        imageUrl = image_url,
        phoneNumber = phone_number,
        userId = user_id
    )
}

fun UserDto.toDB(): UserEntity{
    return UserEntity(
        firstName = this.firstname,
        lastName = this.lastname,
        email = this.email,
        latitude = this.latitude,
        longitude = this.longitude,
        userId = this.user_id,
        phoneNumber = this.phone_number
    )
}