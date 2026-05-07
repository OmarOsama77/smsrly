package com.example.smsrly.data.remote.datasource.userdatasource

import com.example.smsrly.data.remote.dto.user.DeleteUserDto
import com.example.smsrly.data.remote.dto.user.UserDto
import com.example.smsrly.data.remote.dto.user.UserInfoDto

interface IUserRemoteDataSource{
    suspend fun getUserData(): Result<UserDto>
    suspend fun deleteUser():Result<DeleteUserDto>
    suspend fun getUsersDataById(usersIds:List<String>):Result<Map<String,UserInfoDto>>
}