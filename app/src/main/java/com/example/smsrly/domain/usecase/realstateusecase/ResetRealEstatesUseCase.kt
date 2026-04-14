package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class ResetRealEstatesUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    fun resetRealEstates(){
        realEstateRepo.resetData()
    }
}