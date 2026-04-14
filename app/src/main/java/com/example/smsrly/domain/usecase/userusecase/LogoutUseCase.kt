package com.example.smsrly.domain.usecase.userusecase

import com.example.smsrly.domain.repository.IUserRepo
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepo: IUserRepo
) {
    fun logout(){
        userRepo.logout()
    }
}