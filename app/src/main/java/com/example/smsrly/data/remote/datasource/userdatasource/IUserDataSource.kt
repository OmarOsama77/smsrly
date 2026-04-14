package com.example.smsrly.data.remote.datasource.userdatasource

import com.example.smsrly.data.remote.dto.user.DeleteUserDto
import com.example.smsrly.data.remote.dto.user.GetUserResponse

interface IUserDataSource{
    suspend fun getUserData(): Result<GetUserResponse>
    suspend fun deleteUser():Result<DeleteUserDto>
}