package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAllRealEstatesUseCase @Inject constructor (
    private val realEstateRepo: IRealEstateRepo
) {

    suspend fun getAllRealEstates(): Result<String> {
        return realEstateRepo.getAllRealEstates()
    }
    fun getRealEstatesObj():StateFlow<Map<Int, RealEstate>>{
        return realEstateRepo.getRealEstateObj()
    }

}