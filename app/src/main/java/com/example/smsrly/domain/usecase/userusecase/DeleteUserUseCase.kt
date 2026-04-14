package com.example.smsrly.domain.usecase.userusecase

import com.example.smsrly.domain.repository.IUserRepo
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepo: IUserRepo
){
    suspend fun deleteUser():Result<String>{
        return userRepo.deleteUser()
    }
}