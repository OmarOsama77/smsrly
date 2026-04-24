package com.example.smsrly.data.remote.apiservice

object ApiConstants {
    const val base_url = "http://192.168.100.8:8080/"
    const val send_otp = "auth/verify-email"
    const val signup = "auth/signup"
    const val login = "auth/login"
    const val userData = "user"
    const val userImage = "image/user"
    const val uploadRealState = "real-estate"
    const val refreshToken = "auth/refresh-token"
    const val uploadRealEstateImage = "image/real-estate"
    const val getAllRealEstates = "/real-estate/all?page=0&size=500"
    const val getNearestRealEstates = "/real-estate?page=0&size=500"
    const val getUserUploads = "/user/uploads"
    const val sendRequest = "/request/{id}"
    const val saveARealEstate = "/save/{id}"
    const val getUserSavedRealEstate = "/user/saves"
    const val getUserRequests = "/user/requests"
    const val searchByTitle = "/real-estate/filter"
    const val deleteUser ="/user"
    const val getUsersDataByIds="/user/uploaders-info"
}