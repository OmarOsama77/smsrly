package com.example.smsrly.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId:Int,
    val userNumber: String,
    val userImage: String?,
    val userName: String
)