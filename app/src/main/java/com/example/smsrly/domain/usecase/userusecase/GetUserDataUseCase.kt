package com.example.smsrly.domain.usecase.userusecase

import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.repository.IUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(private val userRepo: IUserRepo) {

    suspend fun fetchUserData():Result<Unit>{
       return userRepo.getUserData()
    }

}