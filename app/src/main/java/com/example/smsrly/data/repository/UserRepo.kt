package com.example.smsrly.data.repository

import com.example.smsrly.data.mapper.toDomain
import com.example.smsrly.data.remote.datasource.userdatasource.IUserDataSource
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.repository.IUserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(
    private val userDataSource: IUserDataSource
) : IUserRepo {

    private val _user = MutableStateFlow<User?>(null)


    override suspend fun getUserData(): Result<Unit> {
        if (_user.value == null) {
            val res = userDataSource.getUserData()
            if (res.isSuccess) {
                _user.value = res.getOrNull()!!.toDomain()
                return Result.success(Unit)
            } else {
                return Result.failure(res.exceptionOrNull()!!)
            }
        }
        return Result.success(Unit)
    }

    override fun getUserFlow(): StateFlow<User?> {
        return _user
    }

    override fun logout() {
        _user.value = null
    }

    override suspend fun deleteUser(): Result<String> {
        val res = userDataSource.deleteUser()
        if(res.isSuccess){
            return Result.success(res.getOrNull()?.message?:"Account deleted")
        }else{
            return Result.failure(res.exceptionOrNull()!!)
        }
    }

}