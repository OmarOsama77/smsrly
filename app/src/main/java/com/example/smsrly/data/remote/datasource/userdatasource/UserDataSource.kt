package com.example.smsrly.data.remote.datasource.userdatasource

import android.util.Log
import com.example.smsrly.data.remote.apiservice.ApiConstants.base_url
import com.example.smsrly.data.remote.apiservice.ApiService
import com.example.smsrly.data.remote.dto.user.DeleteUserDto
import com.example.smsrly.data.remote.dto.user.GetUserResponse

import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val apiService : ApiService
) : IUserDataSource {
    override suspend fun getUserData(): Result<GetUserResponse> {
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
}