package com.example.smsrly.data.mapper

import com.example.smsrly.data.remote.dto.login.LoginSuccessDto
import com.example.smsrly.data.remote.dto.signup.SignupSuccessDto
import com.example.smsrly.data.remote.dto.user.GetUserResponse
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



fun GetUserResponse.toDomain(): User{
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
