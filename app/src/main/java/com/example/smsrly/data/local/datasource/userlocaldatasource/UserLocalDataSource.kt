package com.example.smsrly.data.local.datasource.userlocaldatasource

import com.example.smsrly.data.local.db.SmsrlyDataBaseDao
import com.example.smsrly.data.local.db.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val db : SmsrlyDataBaseDao
): IUserLocalDataSource {
    override suspend fun saveUser(user: UserEntity) {
        db.saveUser(user)
    }

    override  fun getUser(): Flow<UserEntity> {
       return db.getUser()
    }


}