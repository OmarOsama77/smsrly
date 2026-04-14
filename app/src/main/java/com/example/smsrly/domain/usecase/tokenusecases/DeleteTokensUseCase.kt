package com.example.smsrly.domain.usecase.tokenusecases

import com.example.smsrly.domain.repository.ITokenRepo
import javax.inject.Inject

class DeleteTokensUseCase @Inject constructor(
    private val tokenRepo: ITokenRepo
) {
    fun resetToken():Boolean{
       return tokenRepo.resetTokens()
    }
}