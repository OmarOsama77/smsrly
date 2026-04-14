package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSavedRealEstatesUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun getUserSavedRealEstates(): Flow<List<RealEstate>> {
        return realEstateRepo.getUserSavedRealEstates()
    }
}