package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserRequestsUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun getUserRequests(): Flow<List<RealEstate>> {
        return realEstateRepo.getUserRequests()
    }
}