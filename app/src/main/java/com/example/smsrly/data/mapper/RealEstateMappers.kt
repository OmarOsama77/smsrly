package com.example.smsrly.data.mapper

import com.example.smsrly.data.remote.apiservice.ApiConstants
import com.example.smsrly.data.remote.dto.realestate.getrealestates.ContentItemDto
import com.example.smsrly.data.remote.dto.realestate.getrealestates.GetRealEstatesDto
import com.example.smsrly.data.remote.dto.realestate.getrealestates.UserInfoDto
import com.example.smsrly.data.remote.dto.realestate.getuseruploads.GetUserUploadsDto
import com.example.smsrly.data.remote.dto.realestate.getuseruploads.UserUploadedRealEstateDto
import com.example.smsrly.data.remote.dto.realestate.uploadRealState.UploadRealStateDto
import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.models.UserInfo


fun RealEstate.toUploadDto(): UploadRealStateDto {
    return UploadRealStateDto(
        title = title,
        description = desc,
        area = area,
        floor_number = floor,
        bathroom_number = bathRoom,
        room_number = rooms,
        price = price,
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0,
        city = city,
        country = country,
        is_sale = false
    )

}

fun UserInfoDto.toDomain(): UserInfo{
return UserInfo(
    userNumber = this.user_number,
    userImage = "${ApiConstants.base_url.dropLast(1)}${this.user_image}",
    userName = this.username
)
}

fun ContentItemDto.toDomain(): RealEstate {
    return RealEstate(
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
        images = this.images.map {image->
            "${ApiConstants.base_url.dropLast(1)}${image}"
        },
        userInfo = this.userInfo.toDomain(),
        id = this.id,
        isSaved = this.is_save,
        isRequested = this.is_requested,
        requestedUsers = null
    )
}
fun GetRealEstatesDto.toDomain(): List<RealEstate>{
    return content.map {
        it.toDomain()
    }
}
fun GetUserUploadsDto.toDomain(): List<RealEstate>{
    return content.map {
        it.toDomain()
    }
}

fun UserUploadedRealEstateDto.toDomain(): RealEstate{
    return RealEstate(
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
        id = this.id,
        isSaved = null,
        isRequested = null,
        images = this.images.map {
            "${ApiConstants.base_url.dropLast(1)}${it}"
        },
        rooms = this.room_number,
        userInfo = null,
        requestedUsers = this.requests.map {
            it.toDomain()
        }
    )
}
