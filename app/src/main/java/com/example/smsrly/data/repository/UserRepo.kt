package com.example.smsrly.data.repository

import android.util.Log
import com.example.smsrly.data.local.datasource.userlocaldatasource.IUserLocalDataSource
import com.example.smsrly.data.local.db.entities.UserEntity
import com.example.smsrly.data.mapper.toDB
import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.remote.datasource.userdatasource.IUserRemoteDataSource
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.models.UserInfo
import com.example.smsrly.domain.observer.IConnectivityObserver
import com.example.smsrly.domain.repository.IUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(
    private val userRemoteDataSource: IUserRemoteDataSource,
    private val userLocalDataSource: IUserLocalDataSource,
    private val connectionState: IConnectivityObserver
) : IUserRepo {


    override suspend fun getUser(): Flow<User> {
        return userLocalDataSource.getUser().map {
            it.toDomain()
        }
    }

    override suspend fun getUserFromServer():Result<User>{
        val res = userRemoteDataSource.getUserData()
        if (res.isSuccess) {
            val userDto = res.getOrNull()!!
            saveUserInDB(userDto.toDB())
            return Result.success(userDto.toDomain())
        }
        return Result.failure(Exception(res.exceptionOrNull()?.message))
    }


    suspend fun saveUserInDB(user: UserEntity) {
        userLocalDataSource.saveUser(user)
    }

    override fun logout() {
//        _user.value = null
    }

    override suspend fun deleteUser(): Result<String> {
        val res = userRemoteDataSource.deleteUser()
        if (res.isSuccess) {
            return Result.success(res.getOrNull()?.message ?: "Account deleted")
        } else {
            return Result.failure(res.exceptionOrNull()!!)
        }
    }

    override suspend fun getUsersInfo(usersIds: List<String>): Result<Map<String, UserInfo>> {
        return userRemoteDataSource.getUsersDataById(usersIds).map { oldMap ->
            oldMap.mapValues {
                it.value.toDomain()
            }
        }
    }

}