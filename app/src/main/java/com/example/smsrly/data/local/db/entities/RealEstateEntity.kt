package com.example.smsrly.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RealEstate")
data class RealEstateEntity(
    val title: String,
    val desc: String,
    val area: Double,
    val floor: Int,
    val bathRoom: Int,
    val price: Double,
    val city: String,
    val country: String,
    val latitude: Double,
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val longitude: Double,
    val rooms: Int,
    val images: List<String>,
    val uploaderInfo: UserInfoEntity,
    var isRequested :Boolean,
    var isSaved : Boolean
)