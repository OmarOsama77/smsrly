package com.example.smsrly.domain.usecase.userusecase

import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.repository.IUserRepo
import javax.inject.Inject

class GetUserFromServerUseCase @Inject constructor(
    private val userRepo: IUserRepo
) {
    suspend operator fun invoke():Result<User>{
        return userRepo.getUserFromServer()
    }
}