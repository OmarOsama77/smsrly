package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class UnSaveARealEstateUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun unSaveARealEstate(id:Int):Result<String>{
        return realEstateRepo.unSaveARealEstate(id)
    }
}