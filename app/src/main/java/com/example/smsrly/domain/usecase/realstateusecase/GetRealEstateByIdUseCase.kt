package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRealEstateByIdUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
){
    fun getRealEstateById(id:Int): Flow<RealEstate> {
        return realEstateRepo.getRealEstateById(id)
    }
}