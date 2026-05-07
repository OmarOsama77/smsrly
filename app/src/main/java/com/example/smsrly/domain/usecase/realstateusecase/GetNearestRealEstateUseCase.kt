package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetNearestRealEstateUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {

    suspend operator fun invoke(): Flow<List<RealEstate>> {
        return realEstateRepo.getNeatestRealEstates()
    }

}