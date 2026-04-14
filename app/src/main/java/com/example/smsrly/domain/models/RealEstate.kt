package com.example.smsrly.domain.models

import kotlinx.serialization.Serializable

@Serializable

data class RealEstate(
    val title: String,
    val desc: String,
    val area: Double,
    val floor: Int,
    val bathRoom: Int,
    val price: Double,
    val city: String,
    val country: String,
    val latitude: Double,
    val id : Int?,
    val longitude: Double,
    val rooms: Int,
    val images: List<String>,
    val userInfo: UserInfo?,
    var isRequested :Boolean?,
    var isSaved : Boolean?,
    val requestedUsers : List<UserInfo>?
)

