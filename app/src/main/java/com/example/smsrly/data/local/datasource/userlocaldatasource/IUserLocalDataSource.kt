package com.example.smsrly.data.local.datasource.userlocaldatasource

import com.example.smsrly.data.local.db.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface IUserLocalDataSource {
    suspend fun saveUser(user: UserEntity)
     fun getUser(): Flow<UserEntity>

}