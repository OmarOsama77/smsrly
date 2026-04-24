package com.example.smsrly.utility

import android.os.Bundle
import androidx.navigation.NavType
import com.example.smsrly.domain.models.User
import com.example.smsrly.domain.models.UserInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

object UserInfoNavType : NavType<UserInfo>(isNullableAllowed = false){
    override fun put(bundle: Bundle, key: String, value: UserInfo) {
        bundle.putString(key, Json.encodeToString(value))
    }
    override fun get(bundle: Bundle, key: String): UserInfo {
        return Json.decodeFromString(bundle.getString(key)!!)
    }

    override fun parseValue(value: String): UserInfo {
        return Json.decodeFromString(URLDecoder.decode(value, "UTF-8"))
    }

    override fun serializeAsValue(value: UserInfo): String {
        return URLEncoder.encode(Json.encodeToString(value), "UTF-8")
    }

}