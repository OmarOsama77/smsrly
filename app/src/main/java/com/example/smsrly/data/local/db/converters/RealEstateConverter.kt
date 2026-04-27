package com.example.smsrly.data.local.db.converters

import androidx.room.TypeConverter
import com.example.smsrly.data.local.db.entities.UserInfoEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RealEstateConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromStringList(list:List<String>):String{
        return gson.toJson(list)
    }
    @TypeConverter
    fun toStringList(value:String):List<String>{
        return gson.fromJson(value,object  : TypeToken<List<String>>() {}.type)
    }
    @TypeConverter
    fun fromUserInfo(userInfo: UserInfoEntity?): String = gson.toJson(userInfo)

    @TypeConverter
    fun toUserInfo(value: String): UserInfoEntity? =
        gson.fromJson(value, UserInfoEntity::class.java)

//    @TypeConverter
//    fun fromUserInfoList(list: List<UserInfoEntity>?): String = gson.toJson(list)
//
//    @TypeConverter
//    fun toUserInfoList(value: String): List<UserInfoEntity>? =
//        gson.fromJson(value, object : TypeToken<List<UserInfoEntity>>() {}.type)
}