package com.example.smsrly.domain.usecase.userusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSavedRealEstatesUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    fun getUserSavedRealEstates(): Flow<List<RealEstate>> {
        return realEstateRepo.getUserSavedRealEstates()
    }
}