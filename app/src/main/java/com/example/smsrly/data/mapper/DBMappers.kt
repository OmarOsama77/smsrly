package com.example.smsrly.data.mapper

import com.example.smsrly.data.local.db.entities.RealEstateEntity
import com.example.smsrly.data.local.db.entities.UserInfoEntity
import com.example.smsrly.data.remote.apiservice.ApiConstants
import com.example.smsrly.data.remote.dto.realestate.getrealestates.GetRealEstatesDto
import com.example.smsrly.data.remote.dto.realestate.getrealestates.RealEstateDto
import com.example.smsrly.data.remote.dto.user.UserInfoDto
import com.example.smsrly.domain.models.RealEstate
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