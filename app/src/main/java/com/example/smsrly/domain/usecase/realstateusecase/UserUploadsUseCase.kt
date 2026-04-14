package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class UserUploadsUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun getUserUploads():Result<List<RealEstate>>{
        return realEstateRepo.getUserUploads()
    }
}