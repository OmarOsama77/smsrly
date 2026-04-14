package com.example.smsrly.presentation.ui.screens.auth.signup.viewmodel

sealed class SignupEvents{
    data class SignupSuccess(val message:String): SignupEvents()
    data class SignupFailed(val message: String) : SignupEvents()
    data class ImageUploadFailed(val message:String) : SignupEvents()
}