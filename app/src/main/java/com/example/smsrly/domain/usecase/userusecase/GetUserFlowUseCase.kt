package com.example.smsrly.domain.usecase.userusecase

import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.repository.IUserRepo
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetUserFlowUseCase @Inject constructor(
    private val userRepo: IUserRepo
) {
    fun getUser():StateFlow<User?>{
        return userRepo.getUserFlow()
    }

}