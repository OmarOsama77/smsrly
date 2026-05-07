package com.example.smsrly.data.mapper

import com.example.smsrly.data.local.db.entities.RealEstateEntity
import com.example.smsrly.data.local.db.entities.UserEntity
import com.example.smsrly.data.local.db.entities.UserInfoEntity
import com.example.smsrly.data.remote.apiservice.ApiConstants
import com.example.smsrly.data.remote.dto.realestate.getrealestates.GetRealEstatesDto
import com.example.smsrly.data.remote.dto.realestate.getrealestates.RealEstateDto
import com.example.smsrly.data.remote.dto.user.UserInfoDto
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.models.UserInfo

fun RealEstateDto.toDB(): RealEstateEntity{
    return RealEstateEntity(
        id = this.id,
        title = this.title,
        desc = this.description,
        area = this.area,
        floor = this.floor_number,
        bathRoom = this.bathroom_number,
        price = this.price,
        city = this.city,
        country = this.country,
        latitude = this.latitude,
        longitude = this.longitude,
        rooms = this.room_number,
        images = this.images,
        uploaderInfo = this.uploaderInfo.toDB(),
        isRequested = this.is_requested,
        isSaved = this.is_save,
    )
}

fun UserInfoDto.toDB(): UserInfoEntity{
    return UserInfoEntity(
        userId = this.user_id,
        userNumber = this.user_number,
        userImage =this.user_image?.let {
            "${ApiConstants.base_url.dropLast(1)}$it"
        },
        userName = this.username
    )
}

fun RealEstateEntity.toDomain(): RealEstate{
    return RealEstate(
        title = title,
        desc = desc,
        area = area,
        floor = floor,
        bathRoom = bathRoom,
        price = price,
        city = city,
        country = country,
        latitude = latitude,
        id = id,
        longitude = longitude,
        rooms = rooms,
        images = images.map {
            "${ApiConstants.base_url.dropLast(1)}$it"
        },
        uploaderInfo = uploaderInfo.toDomain(),
        isRequested = isRequested,
        isSaved = isSaved,
        requestedUsers = emptyList()
    )
}
fun UserInfoEntity.toDomain():UserInfo{
    return UserInfo(
        userId = userId,
        userNumber = userNumber,
        userImage = userImage,
        userName = userName
    )
}


fun User.toDB(): UserEntity{
    return UserEntity(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        latitude = this.latitude!!,
        longitude = this.longitude!!,
        phoneNumber = this.phoneNumber,
        userId = this.userId!!
    )
}


