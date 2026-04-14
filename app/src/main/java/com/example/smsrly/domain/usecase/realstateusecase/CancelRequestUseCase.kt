package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class CancelRequestUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun cancelRequest(id:Int):Result<String>{
        return realEstateRepo.cancelRequest(id)
    }
}