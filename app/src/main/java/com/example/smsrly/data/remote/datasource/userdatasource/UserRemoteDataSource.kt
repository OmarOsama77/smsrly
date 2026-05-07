package com.example.smsrly.data.remote.datasource.userdatasource

import android.util.Log
import com.example.smsrly.data.remote.apiservice.ApiConstants.base_url
import com.example.smsrly.data.remote.apiservice.ApiService
import com.example.smsrly.data.remote.dto.user.DeleteUserDto
import com.example.smsrly.data.remote.dto.user.UserDto
import com.example.smsrly.data.remote.dto.user.GetUsersDataDtoRequest
import com.example.smsrly.data.remote.dto.user.UserInfoDto
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val apiService : ApiService
) : IUserRemoteDataSource {
    override suspend fun getUserData(): Result<UserDto> {
        try {
            val response = apiService.getUserData()
            if (response.isSuccessful) {
                if (response.body()?.image_url != null) {
                    response.body()!!.image_url =
                        base_url.trimEnd('/') + response.body()!!.image_url
                }
                return Result.success(response.body()!!)
            } else {
                return Result.failure(Exception("Failed getting user data"))
            }
        } catch (e: Exception) {
            return Result.failure(Exception("Network issue"))
        }

    }

    override suspend fun deleteUser(): Result<DeleteUserDto> {
        try{
            val res = apiService.deleteUser()
            if(res.isSuccessful){
                return Result.success(res.body()!!)
            }
            return Result.failure(Exception("Failed deleting user"))
        }catch (e : Exception){
            return Result.failure(Exception("Network issue"))
        }
    }

    override suspend fun getUsersDataById(usersIds: List<String>): Result<Map<String,UserInfoDto>> {
        try{
            val request = GetUsersDataDtoRequest(usersIds)
            val res = apiService.getUsersDataByIds(request)
            if(res.isSuccessful){
             return Result.success(res.body()!!)
            }
            return Result.failure(Exception("Failed"))
        }catch (e : Exception){
            Log.d("ya omar catch the error ",e.toString())
            return Result.failure(Exception("Network issue"))
        }
    }
}