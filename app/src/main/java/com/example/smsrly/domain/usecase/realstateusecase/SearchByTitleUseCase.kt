package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.models.RealEstate
import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class SearchByTitleUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
) {
    suspend fun searchByTitle(title : String):Result<List<RealEstate>>{
        return realEstateRepo.searchByTitle(title)
    }
}