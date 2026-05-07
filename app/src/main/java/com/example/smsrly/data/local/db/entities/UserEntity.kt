package com.example.smsrly.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    val firstName:String,
    val lastName: String,
    val email:String,
    val latitude: Double,
    val longitude:Double,
    @PrimaryKey(autoGenerate = false)
    val userId:Int,
    val phoneNumber:String,
)