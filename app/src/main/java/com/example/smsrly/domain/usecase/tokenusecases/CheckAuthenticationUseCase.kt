package com.example.smsrly.domain.usecase.tokenusecases

import android.util.Log
import com.example.smsrly.domain.repository.ITokenRepo
import com.example.smsrly.utility.JwtTokenValidator
import javax.inject.Inject

class CheckAuthenticationUseCase @Inject constructor(private val tokenRepo: ITokenRepo) {
     fun hasValidRefreshToken(): Boolean {
        val token = tokenRepo.getRefreshToken()
         val s = tokenRepo.getAccessToken()
         Log.d("The token ",token?:"d")
        if (JwtTokenValidator.isTokenValid(token)) {
            return true
        } else {
            Log.d("The token ","invalid")
            return false
        }
    }



}