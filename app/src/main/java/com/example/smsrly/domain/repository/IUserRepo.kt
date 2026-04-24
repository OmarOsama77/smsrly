package com.example.smsrly.domain.repository

import com.example.smsrly.data.remote.dto.user.UserInfoDto
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.models.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IUserRepo {
    suspend fun getUserData():Result<Unit>
    fun getUserFlow():StateFlow<User?>
    fun logout()
    suspend fun deleteUser():Result<String>
    suspend fun getUsersInfo(usersIds: List<String>):Result<Map<String,UserInfo>>
}