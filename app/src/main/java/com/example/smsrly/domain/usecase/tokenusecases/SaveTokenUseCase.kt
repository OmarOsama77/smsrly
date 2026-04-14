package com.example.smsrly.domain.usecase.tokenusecases

import com.example.smsrly.domain.repository.ITokenRepo
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(private val tokenRepo: ITokenRepo) {

     fun saveTokens(accessToken:String,refreshToken:String){
         tokenRepo.saveTokens(accessToken,refreshToken)
    }
}