package com.example.smsrly.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInfo")
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val userId:Int,
    val userNumber: String,
    val userImage: String?,
    val userName: String
)