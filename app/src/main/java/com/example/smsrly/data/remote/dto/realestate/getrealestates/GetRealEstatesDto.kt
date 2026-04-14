package com.example.smsrly.data.remote.dto.realestate.getrealestates

data class GetRealEstatesDto(
    val last : Boolean,
    val content:List<ContentItemDto>,
    val page_number:Int,
    val page_size:Int
)