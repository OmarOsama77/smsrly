package com.example.smsrly.data.remote.dto.realestate.getuseruploads

import com.example.smsrly.data.remote.dto.realestate.uploadRealState.UploadRealStateDto

data class GetUserUploadsDto(
    val last: Boolean,
    val content:List<UserUploadedRealEstateDto>,
    val page_number:Int,
    val page_size:Int
)