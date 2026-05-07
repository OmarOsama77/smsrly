package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class SaveARealEstateUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun saveARealEstate(id: Int) :Result<String>{
        return realEstateRepo.saveARealEstate(id)
    }
}