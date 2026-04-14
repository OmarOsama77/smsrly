package com.example.smsrly.utility

import android.os.Bundle
import androidx.navigation.NavType
import com.example.smsrly.domain.models.RealEstate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder


object RealEstateNavType : NavType<RealEstate>(isNullableAllowed = false){
    override fun put(bundle: Bundle, key: String, value: RealEstate) {
        bundle.putString(key, Json.encodeToString(value))
    }
    override fun get(bundle: Bundle, key: String): RealEstate {
        return Json.decodeFromString(bundle.getString(key)!!)
    }

    override fun parseValue(value: String): RealEstate {
        return Json.decodeFromString(URLDecoder.decode(value, "UTF-8"))
    }

    override fun serializeAsValue(value: RealEstate): String {
        return URLEncoder.encode(Json.encodeToString(value), "UTF-8")
    }

}