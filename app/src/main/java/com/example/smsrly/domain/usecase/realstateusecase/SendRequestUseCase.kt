package com.example.smsrly.domain.usecase.realstateusecase

import com.example.smsrly.domain.repository.IRealEstateRepo
import javax.inject.Inject

class SendRequestUseCase @Inject constructor(
    private val realEstateRepo: IRealEstateRepo
){
   suspend fun sendRequest(id : Int):Result<String>{
         return realEstateRepo.sendRequest(id)
    }
}